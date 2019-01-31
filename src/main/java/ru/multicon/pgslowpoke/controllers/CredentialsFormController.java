package ru.multicon.pgslowpoke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.multicon.pgslowpoke.domain.PgCredentials;

@Controller
@SessionAttributes("pgCredentials")
public class CredentialsFormController {

    @ModelAttribute("pgCredentials")
    protected PgCredentials getPgCredentials() {
        return new PgCredentials()
                    .setHost("db-for-test")
                    .setPort("5432")
                    .setDatabase("analytics")
                    .setUser("analytics")
                    .setPassword("passwordanalytics");
    }

    @GetMapping(path = {"/credentials", "", "/", "index"})
    public String credentials() {
        return "credentials";
    }

    @PostMapping("/inputcredentials")
    public String inputCredentials() {
        return "forward:dbsize";
    }
}
