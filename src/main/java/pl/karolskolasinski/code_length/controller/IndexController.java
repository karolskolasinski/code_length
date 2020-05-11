package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.model.dto.ObjectToDisplay;
import pl.karolskolasinski.code_length.service.UserCodeLengthService;
import pl.karolskolasinski.code_length.utils.NumberOfReposUtil;

import java.security.Principal;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private UserCodeLengthService uclService;
    private NumberOfReposUtil numberOfReposUtil;

    @Autowired
    public IndexController(UserCodeLengthService uclService, NumberOfReposUtil numberOfReposUtil) {
        this.uclService = uclService;
        this.numberOfReposUtil = numberOfReposUtil;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("top10", uclService.top10());
        return "index";
    }

    @PostMapping(value = "/get")
    public String getInfo(Model model, @ModelAttribute("username") String username) {
        if (uclService.incorrectUsername(username)) return onError(model, "You need to enter a username.");
        if (numberOfReposUtil.getNumberOfPublicRepos(username) == -2) return onError(model, "User not found.");

        ObjectToDisplay objectToDisplay = uclService.getUserDetails(username);

        model.addAttribute("username", username);
        model.addAttribute("numberOfPublicRepos", objectToDisplay.getNumberOfPublicRepos());
        model.addAttribute("length", objectToDisplay.getLength());
        model.addAttribute("language", objectToDisplay.getLanguage());
        model.addAttribute("repos", objectToDisplay.getReposNames());
        uclService.saveUserToDatabase(objectToDisplay);
        return "result";
    }

    private String onError(Model model, String errorMessage) {
        model.addAttribute("top10", uclService.top10());
        model.addAttribute("errorMessage", errorMessage);
        return "index";
    }

    @GetMapping("/authenticated")
    public String authenticated(Model model, Principal principal, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        String tokenValue = accessToken.getTokenValue();

        System.out.println();
        System.out.println(tokenValue);
        System.out.println(principal.getName());
        System.out.println();

        return getInfo(model, principal.getName());
    }

}
