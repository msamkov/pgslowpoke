package ru.multicon.pgslowpoke.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.services.IndexUnusedService;

@Slf4j
@Controller
@SessionAttributes("pgCredentials")
public class IndexUnusedController {

    private final IndexUnusedService indexUnusedService;

    @Autowired
    public IndexUnusedController(IndexUnusedService indexUnusedService) {
        this.indexUnusedService = indexUnusedService;
    }

    @RequestMapping({"/indexunused"})
    public String findAll(Model model, @ModelAttribute("pgCredentials") PgCredentials pgCredentials){
        log.debug("Getting Index unused");

        model.addAttribute("indexUnused", indexUnusedService.findAll(pgCredentials));

        return "indexUnused";
    }

    @ExceptionHandler
    private String exceptionHandler(Exception e) {
        return "forward:credentials";
    }
}