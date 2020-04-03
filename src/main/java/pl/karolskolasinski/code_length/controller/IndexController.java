package pl.karolskolasinski.code_length.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.service.GithubService;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private GithubService githubService;

    @Autowired
    public IndexController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/get")
    public String getLength(String username, Model model) {
        model.addAttribute("numberOfRepos", githubService.numberOfRepos(username));
        model.addAttribute("length", githubService.codeLengthMeter(username));
        model.addAttribute("language", githubService.language(username));
        return "codelength";
    }

}
