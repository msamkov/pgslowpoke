package ru.multicon.pgslowpoke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexUnusedController {

    @RequestMapping({"/indexunused"})
    public String index(){
        return "indexUnused";
    }
}