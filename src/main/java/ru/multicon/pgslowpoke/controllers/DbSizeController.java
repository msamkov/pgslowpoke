package ru.multicon.pgslowpoke.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.services.DbSizeService;
import ru.multicon.pgslowpoke.services.IndexSizeService;
import ru.multicon.pgslowpoke.services.TableSizeService;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DbSizeController {

    private final DbSizeService dbSizeService;
    private final IndexSizeService indexSizeService;
    private final TableSizeService tableSizeService;
    private final DbSizeToDbSizeDto dbSizeToDbSizeDto;
    private final IndexSizeToIndexSizeDto indexSizeToIndexSizeDto;

    @Autowired
    public DbSizeController(DbSizeService dbSizeService, IndexSizeService indexSizeService, TableSizeService tableSizeService, DbSizeToDbSizeDto dbSizeToDbSizeDto, IndexSizeToIndexSizeDto indexSizeToIndexSizeDto) {
        this.dbSizeService = dbSizeService;
        this.indexSizeService = indexSizeService;
        this.tableSizeService = tableSizeService;
        this.dbSizeToDbSizeDto = dbSizeToDbSizeDto;
        this.indexSizeToIndexSizeDto = indexSizeToIndexSizeDto;
    }

    @RequestMapping({"/dbsize"})
    public String findAll(Model model) {
        log.debug("Getting db size");

        model.addAttribute("dbSize", dbSizeToDbSizeDto.convert(dbSizeService.current()));
        model.addAttribute("indexSize",
                indexSizeService.findAll().stream()
                                          .map(indexSizeToIndexSizeDto::convert)
                                          .collect(Collectors.toList()));
        model.addAttribute("tableSize", tableSizeService.findAll());

        return "dbSize";
    }
}
