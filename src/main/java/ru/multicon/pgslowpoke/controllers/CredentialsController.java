package ru.multicon.pgslowpoke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CredentialsController {

    @GetMapping("/credentials")
    public String credentials(Model model) {
        return "credentials";
    }
}
