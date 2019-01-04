package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;

import java.util.List;

@Service
public class PgStatUserTablesService {

    private final PgStatUserTablesRepository PgStatUserTablesRepository;

    @Autowired
    public PgStatUserTablesService(PgStatUserTablesRepository PgStatUserTablesRepository) {
        this.PgStatUserTablesRepository = PgStatUserTablesRepository;
    }

    public List<PgStatUserTables> findAll() {
        return PgStatUserTablesRepository.findAll();
    }
}
