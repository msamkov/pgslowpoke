package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class ForeignKeyDuplicateService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public ForeignKeyDuplicateService(MyBatisMapperFactory myBatisMapperFactory, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<ForeignKeyDuplicate> findAll(PgCredentials pgCredentials) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            ForeignKeyDuplicateRepository foreignKeyDuplicateRepository = getForeignKeyDuplicateRepository(dataSource);
            return foreignKeyDuplicateRepository.findAll();
        }
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected ForeignKeyDuplicateRepository getForeignKeyDuplicateRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, ForeignKeyDuplicateRepository.class);
    }
}
