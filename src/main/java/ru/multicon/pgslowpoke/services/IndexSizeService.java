package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;
import ru.multicon.pgslowpoke.services.dto.IndexSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexSizeService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final IndexSizeToIndexSizeDto indexSizeToIndexSizeDto;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public IndexSizeService(MyBatisMapperFactory myBatisMapperFactory, IndexSizeToIndexSizeDto indexSizeToIndexSizeDto, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.indexSizeToIndexSizeDto = indexSizeToIndexSizeDto;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<IndexSizeDto> findAll(PgCredentials pgCredentials) {
        HikariDataSource dataSource = getHikariDataSource(pgCredentials);
        IndexSizeRepository indexSizeRepository = getIndexSizeRepository(dataSource);
        List<IndexSizeDto> indexSizeDtos = indexSizeRepository
                .findAll()
                .stream()
                .map(indexSizeToIndexSizeDto::convert)
                .collect(Collectors.toList());
        dataSource.close();
        return indexSizeDtos;
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected IndexSizeRepository getIndexSizeRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, IndexSizeRepository.class);
    }
}
