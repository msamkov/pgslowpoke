package ru.multicon.pgslowpoke.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.services.IndexUnusedService;

import java.util.stream.Collectors;

@Slf4j
@Controller
public class IndexUnusedController {

    private final IndexUnusedService indexUnusedService;

    @Autowired
    public IndexUnusedController(IndexUnusedService indexUnusedService) {
        this.indexUnusedService = indexUnusedService;
    }

    @RequestMapping({"/indexunused"})
    public String findAll(Model model){
        log.debug("Getting Index unused");

        model.addAttribute("indexUnused", indexUnusedService.findAll());

        return "indexUnused";
    }
}