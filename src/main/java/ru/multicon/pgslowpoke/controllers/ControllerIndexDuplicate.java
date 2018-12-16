package ru.multicon.pgslowpoke.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.services.ServiceIndexDuplicate;

@Slf4j
@Controller
public class ControllerIndexDuplicate {
    private final ServiceIndexDuplicate serviceIndexDuplicate;

    @Autowired
    public ControllerIndexDuplicate(ServiceIndexDuplicate serviceIndexDuplicate) {
        this.serviceIndexDuplicate = serviceIndexDuplicate;
    }

    @RequestMapping({"/indexduplicate"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Duplicate");

        model.addAttribute("indexduplicate", serviceIndexDuplicate.findAll());

        return "indexduplicate";
    }
}
