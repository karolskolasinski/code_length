package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.service.GithubService;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private GithubService githubService;

    @Autowired
    public IndexController() {
        this.githubService = new GithubService();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/get")
    public String getLength(Model model, @ModelAttribute("username") String username) {
        model.addAttribute("username", username);
        model.addAttribute("numberOfRepos", githubService.numberOfRepos(username));
        model.addAttribute("length", githubService.codeLengthMeter());
        model.addAttribute("language", githubService.language(username));
        return "codelength";
    }

}
