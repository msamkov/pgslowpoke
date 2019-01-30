package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexUnusedService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public IndexUnusedService(MyBatisMapperFactory myBatisMapperFactory, IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.indexUnusedToIndexUnusedDto = indexUnusedToIndexUnusedDto;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<IndexUnusedDto> findAll(PgCredentials pgCredentials) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            IndexUnusedRepository indexUnusedRepository = getIndexUnusedRepository(dataSource);
            return indexUnusedRepository
                    .findAll()
                    .stream()
                    .map(indexUnusedToIndexUnusedDto::convert)
                    .collect(Collectors.toList());
        }

    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected IndexUnusedRepository getIndexUnusedRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, IndexUnusedRepository.class);
    }
}