package ru.multicon.pgslowpoke.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.services.IndexDuplicateService;

@Slf4j
@Controller
public class IndexDuplicateController {

    private final IndexDuplicateService indexDuplicateService;

    @Autowired
    public IndexDuplicateController(IndexDuplicateService indexDuplicateService) {
        this.indexDuplicateService = indexDuplicateService;
    }

    @RequestMapping({"/indexduplicate"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Duplicate");

        model.addAttribute("indexduplicate", indexDuplicateService.findAll());

        return "indexDuplicate";
    }
}
