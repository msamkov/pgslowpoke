package ru.multicon.pgslowpoke.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.services.DbSizeService;
import ru.multicon.pgslowpoke.services.IndexSizeService;
import ru.multicon.pgslowpoke.services.TableSizeService;

import java.util.stream.Collectors;

@Slf4j
@Controller
public class DbSizeController {

    private final DbSizeService dbSizeService;
    private final IndexSizeService indexSizeService;
    private final TableSizeService tableSizeService;

    @Autowired
    public DbSizeController(DbSizeService dbSizeService, IndexSizeService indexSizeService,
                            TableSizeService tableSizeService) {
        this.dbSizeService = dbSizeService;
        this.indexSizeService = indexSizeService;
        this.tableSizeService = tableSizeService;
    }

    @RequestMapping({"/dbsize"})
    public String findAll(Model model) {
        log.debug("Getting db size");

        model.addAttribute("dbSize", dbSizeService.current());
        model.addAttribute("indexSize", indexSizeService.findAll());
        model.addAttribute("tableSize", tableSizeService.findAll());

        return "dbSize";
    }
}
