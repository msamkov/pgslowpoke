package ru.multicon.pgslowpoke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.multicon.pgslowpoke.domain.CredentialsDTO;

@Controller
public class CredentialsFormController {

    @GetMapping("/credentials")
    public String credentials(Model model) {
        model.addAttribute("credentialsDTO", new CredentialsDTO());
        return "credentials";
    }

    @PostMapping("/inputcredentials")
    public String inputCredentials(@ModelAttribute CredentialsDTO credentialsDTO) {
        //TODO сохранять введенный datasource
        return "credentialsSuccess";
    }
}
