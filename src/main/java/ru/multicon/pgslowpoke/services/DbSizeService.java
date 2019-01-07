package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;

@Service
public class DbSizeService {
    private final DbSizeRepository dbSizeRepository;

    @Autowired
    public DbSizeService(DbSizeRepository dbSizeRepository) {
        this.dbSizeRepository = dbSizeRepository;
    }

    public DbSize findByName(String name) {
        return dbSizeRepository.findByName(name);
    }

    public DbSize current() {
        return dbSizeRepository.current();
    }
}
