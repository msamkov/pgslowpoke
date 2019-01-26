package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;

@Service
public class DbSizeService {
    private final DbSizeRepository dbSizeRepository;
    private final DbSizeToDbSizeDto dbSizeToDbSizeDto;

    @Autowired
    public DbSizeService(DbSizeRepository dbSizeRepository, DbSizeToDbSizeDto dbSizeToDbSizeDto) {
        this.dbSizeRepository = dbSizeRepository;
        this.dbSizeToDbSizeDto = dbSizeToDbSizeDto;
    }

    public DbSizeDto findByName(String name) {
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.findByName(name));
    }

    public DbSizeDto current()
    {
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.current());
    }
}
