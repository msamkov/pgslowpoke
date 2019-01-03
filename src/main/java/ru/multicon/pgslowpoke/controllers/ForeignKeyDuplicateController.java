package ru.multicon.pgslowpoke.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.services.ForeignKeyDuplicateService;

@Slf4j
@Controller
public class ForeignKeyDuplicateController {

    private final ForeignKeyDuplicateService foreignKeyDuplicateService;

    @Autowired
    public ForeignKeyDuplicateController(ForeignKeyDuplicateService foreignKeyDuplicateService) {
        this.foreignKeyDuplicateService = foreignKeyDuplicateService;
    }

    @RequestMapping({"/foreignkeyduplicate"})
    public String getIndexPage(Model model) {
        log.debug("Getting Foreign key duplicate");

        model.addAttribute("foreignKeyDuplicate", foreignKeyDuplicateService.findAll());

        return "foreignKeyDuplicate";
    }
}
