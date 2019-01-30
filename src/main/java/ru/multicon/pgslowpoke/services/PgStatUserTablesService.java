package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class PgStatUserTablesService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public PgStatUserTablesService(MyBatisMapperFactory myBatisMapperFactory, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<PgStatUserTables> findAll(PgCredentials pgCredentials) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            PgStatUserTablesRepository pgStatUserTablesRepository = getPgStatUserTablesRepository(dataSource);
            return pgStatUserTablesRepository.findAll();
        }
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected PgStatUserTablesRepository getPgStatUserTablesRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, PgStatUserTablesRepository.class);
    }
}
