package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.model.dto.ObjectToDisplay;
import pl.karolskolasinski.code_length.service.UserCodeLengthService;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private UserCodeLengthService uclService;

    @Autowired
    public IndexController(UserCodeLengthService uclService) {
        this.uclService = uclService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("top10", uclService.top10());
        return "index";
    }

    @PostMapping("/get")
    public String getInfo(Model model, @ModelAttribute("username") String username) {
        if (!uclService.isUsernameCorrect(username)) {
            model.addAttribute("errorMessage", "You need to enter a username.");
            return "index";
        }

        ObjectToDisplay objectToDisplay = uclService.getUserDetails(username);
        model.addAttribute("username", username);

        if (objectToDisplay.getNumberOfPublicRepos() == -2) {
            model.addAttribute("errorMessage", "User not found.");
            return "index";
        }

        model.addAttribute("numberOfPublicRepos", objectToDisplay.getNumberOfPublicRepos());
        model.addAttribute("length", objectToDisplay.getLength());
        model.addAttribute("language", objectToDisplay.getLanguage());
        model.addAttribute("repos", objectToDisplay.getReposNames());
        uclService.saveUserToDatabase(objectToDisplay);
        return "codelength";
    }

}
