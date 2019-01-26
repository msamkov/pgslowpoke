package ru.multicon.pgslowpoke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.multicon.pgslowpoke.domain.PgCredentials;

@Controller
@SessionAttributes("pgCredentials")
public class CredentialsFormController {

    @GetMapping(path = {"/credentials", "", "/", "index"})
    public String credentials(Model model) {
        model.addAttribute("pgCredentials", new PgCredentials());
        return "credentials";
    }

    @PostMapping("/inputcredentials")
    public String inputCredentials(@ModelAttribute("pgCredentials") PgCredentials pgCredentials) {
        //TODO сохранять введенный datasource

        return "forward:dbsize";
    }
}
