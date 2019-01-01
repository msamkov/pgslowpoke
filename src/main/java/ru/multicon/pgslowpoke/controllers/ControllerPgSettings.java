package ru.multicon.pgslowpoke.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.services.ServicePgSettings;


@Slf4j
@Controller
public class ControllerPgSettings {
    private final ServicePgSettings servicePgSettings;

    @Autowired
    public ControllerPgSettings(ServicePgSettings servicePgSettings) {
        this.servicePgSettings = servicePgSettings;
    }

    @RequestMapping({"/pgsettings"})
    public String getIndexPage(Model model) {
        log.debug("Getting PgSettings");

        model.addAttribute("primarySettings", servicePgSettings.findPrimarySettings());

        return "pgsettings";
    }
}