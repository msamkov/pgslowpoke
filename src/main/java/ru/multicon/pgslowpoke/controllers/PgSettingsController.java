package ru.multicon.pgslowpoke.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.services.PgSettingsService;


@Slf4j
@Controller
public class PgSettingsController {

    private final PgSettingsService pgSettingsService;

    @Autowired
    public PgSettingsController(PgSettingsService pgSettingsService) {
        this.pgSettingsService = pgSettingsService;
    }

    @RequestMapping({"/pgsettings"})
    public String getIndexPage(Model model) {
        log.debug("Getting PgSettings");

        model.addAttribute("primarySettings", pgSettingsService.findPrimarySettings());

        return "pgSettings";
    }
}