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
import ru.multicon.pgslowpoke.services.DbSizeService;
import ru.multicon.pgslowpoke.services.IndexSizeService;
import ru.multicon.pgslowpoke.services.TableSizeService;

@Slf4j
@Controller
@SessionAttributes("pgCredentials")
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
    public String findAll(Model model, @ModelAttribute("pgCredentials") PgCredentials pgCredentials) {
        log.debug("Getting db size");

        model.addAttribute("dbSize", dbSizeService.current(pgCredentials));
        model.addAttribute("indexSize", indexSizeService.findAll(pgCredentials));
        model.addAttribute("tableSize", tableSizeService.findAll(pgCredentials));

        return "dbSize";
    }

    @ExceptionHandler
    private String exceptionHandler(Exception e) {
        return "forward:credentials";
    }
}
