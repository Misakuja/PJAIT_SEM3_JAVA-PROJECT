package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("errorText", "null");
        return "homepage";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("errorText", "Wrong url! We moved you to homepage.");
        return "homepage";
    }
}
