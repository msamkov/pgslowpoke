package ru.multicon.pgslowpoke.services;

import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

@Service
public class DbSizeService {
    private final DbSizeToDbSizeDto dbSizeToDbSizeDto;
    private final MyBatisMapper myBatisMapper;

    public DbSizeService(DbSizeToDbSizeDto dbSizeToDbSizeDto, MyBatisMapper myBatisMapper) {
        this.dbSizeToDbSizeDto = dbSizeToDbSizeDto;
        this.myBatisMapper = myBatisMapper;
    }


    public DbSizeDto findByName(PgCredentials pgCredentials, String name) {
        DbSizeRepository dbSizeRepository = myBatisMapper.getMapper(pgCredentials, DbSizeRepository.class);
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.findByName(name));
    }

    public DbSizeDto current(PgCredentials pgCredentials)
    {
        DbSizeRepository dbSizeRepository = myBatisMapper.getMapper(pgCredentials, DbSizeRepository.class);
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.current());
    }
}
