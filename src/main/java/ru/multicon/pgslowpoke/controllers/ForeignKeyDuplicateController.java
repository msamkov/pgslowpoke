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
import ru.multicon.pgslowpoke.services.ForeignKeyDuplicateService;

@Slf4j
@Controller
@SessionAttributes("pgCredentials")
public class ForeignKeyDuplicateController {

    private final ForeignKeyDuplicateService foreignKeyDuplicateService;

    @Autowired
    public ForeignKeyDuplicateController(ForeignKeyDuplicateService foreignKeyDuplicateService) {
        this.foreignKeyDuplicateService = foreignKeyDuplicateService;
    }

    @RequestMapping({"/foreignkeyduplicate"})
    public String findAll(Model model, @ModelAttribute("pgCredentials") PgCredentials pgCredentials) {
        log.debug("Getting Foreign key duplicate");

        model.addAttribute("foreignKeyDuplicate", foreignKeyDuplicateService.findAll(pgCredentials));

        return "foreignKeyDuplicate";
    }

    @ExceptionHandler
    private String exceptionHandler(Exception e) {
        return "forward:credentials";
    }
}
