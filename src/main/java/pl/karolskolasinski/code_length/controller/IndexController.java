package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.service.UserCodeLengthService;
import pl.karolskolasinski.code_length.util.GithubUtil;

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
        GithubUtil gu = new GithubUtil();

        model.addAttribute("username", username);
        model.addAttribute("numberOfRepos", gu.numberOfRepos(username));
        model.addAttribute("length", gu.codeLengthMeter());
        model.addAttribute("language", gu.language(username));
        uclService.saveUserToDatabase(gu.createUser());
        return "codelength";
    }

}
