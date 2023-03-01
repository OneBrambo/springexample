package it.academy.springexample.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController {
    @GetMapping("/welcome")
    public String greeting()   {

        return "greeting";
    }
}