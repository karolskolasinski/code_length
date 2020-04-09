package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.model.UserRepos;
import pl.karolskolasinski.code_length.service.UserCodeLengthService;
import pl.karolskolasinski.code_length.utils.CodeLengthUtil;
import pl.karolskolasinski.code_length.utils.NumberOfReposUtil;
import pl.karolskolasinski.code_length.utils.UserLanguageUtil;

import java.util.Collection;

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
        NumberOfReposUtil numberOfReposUtil = new NumberOfReposUtil();
        CodeLengthUtil codeLengthUtil = new CodeLengthUtil();
        UserLanguageUtil userLanguageUtil = new UserLanguageUtil();

        model.addAttribute("username", username);
        int numberOfPublicRepos = numberOfReposUtil.getNumberOfPublicRepos(username);

        if (numberOfPublicRepos == -2) {
            model.addAttribute("errorMessage", "User not found.");
            return "codelength";
        }

        model.addAttribute("numberOfPublicRepos", numberOfPublicRepos);
        double length = codeLengthUtil.codeLengthMeter(username, numberOfPublicRepos);

        model.addAttribute("length", length);

        Collection<UserRepos> userRepos = codeLengthUtil.getUserRepos();
        String language = userLanguageUtil.userLanguage(userRepos);

        model.addAttribute("language", language);
        model.addAttribute("repos", userLanguageUtil.reposNames(userRepos));
        uclService.saveUserToDatabase(username, numberOfPublicRepos, length, language);

        return "codelength";
    }

}
