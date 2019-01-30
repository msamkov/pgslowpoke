package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

@Service
public class DbSizeService {
    private final DbSizeToDbSizeDto dbSizeToDbSizeDto;
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final DataSourceFactory dataSourceFactory;

    public DbSizeService(DbSizeToDbSizeDto dbSizeToDbSizeDto, MyBatisMapperFactory myBatisMapperFactory, DataSourceFactory dataSourceFactory) {
        this.dbSizeToDbSizeDto = dbSizeToDbSizeDto;
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.dataSourceFactory = dataSourceFactory;
    }

    public DbSizeDto findByName(PgCredentials pgCredentials, String name) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            DbSizeRepository dbSizeRepository = getDbSizeRepository(dataSource);
            return dbSizeToDbSizeDto.convert(dbSizeRepository.findByName(name));
        }
    }

    public DbSizeDto current(PgCredentials pgCredentials)
    {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            DbSizeRepository dbSizeRepository = getDbSizeRepository(dataSource);
            return dbSizeToDbSizeDto.convert(dbSizeRepository.current());
        }
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected DbSizeRepository getDbSizeRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, DbSizeRepository.class);
    }

}
