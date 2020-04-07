package pl.karolskolasinski.code_length.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karolskolasinski.code_length.util.GithubUtil;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/get")
    public String getLength(Model model, @ModelAttribute("username") String username) {
        GithubUtil gu = new GithubUtil();

        model.addAttribute("username", username);
        model.addAttribute("numberOfRepos", gu.numberOfRepos(username));
        model.addAttribute("length", gu.codeLengthMeter());
        model.addAttribute("language", gu.language(username));
        return "codelength";
    }

}
