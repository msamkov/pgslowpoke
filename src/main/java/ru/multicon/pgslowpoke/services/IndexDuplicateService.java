package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class IndexDuplicateService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public IndexDuplicateService(MyBatisMapperFactory myBatisMapperFactory, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<IndexDuplicate> findAll(PgCredentials pgCredentials) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            IndexDuplicateRepository indexDuplicateRepository = getIndexDuplicateRepository(dataSource);
            return indexDuplicateRepository.findAll();
        }
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected IndexDuplicateRepository getIndexDuplicateRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, IndexDuplicateRepository.class);
    }
}
