package ru.multicon.pgslowpoke.services;

import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

@Service
public class DbSizeService {
    private final DbSizeToDbSizeDto dbSizeToDbSizeDto;
    private final MyBatisMapperFactory myBatisMapperFactory;

    public DbSizeService(DbSizeToDbSizeDto dbSizeToDbSizeDto, MyBatisMapperFactory myBatisMapperFactory) {
        this.dbSizeToDbSizeDto = dbSizeToDbSizeDto;
        this.myBatisMapperFactory = myBatisMapperFactory;
    }

    public DbSizeDto findByName(PgCredentials pgCredentials, String name) {
        DbSizeRepository dbSizeRepository = getDbSizeRepository(pgCredentials);
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.findByName(name));
    }

    public DbSizeDto current(PgCredentials pgCredentials)
    {
        DbSizeRepository dbSizeRepository = getDbSizeRepository(pgCredentials);
        return dbSizeToDbSizeDto
                .convert(dbSizeRepository.current());
    }

    protected DbSizeRepository getDbSizeRepository(PgCredentials pgCredentials) {
        return myBatisMapperFactory.create(pgCredentials, DbSizeRepository.class);
    }

}
