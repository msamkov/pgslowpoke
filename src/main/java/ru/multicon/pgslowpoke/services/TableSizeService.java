package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableSizeService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final TableSizeToTableSizeDto tableSizeToTableSizeDto;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public TableSizeService(MyBatisMapperFactory myBatisMapperFactory, TableSizeToTableSizeDto tableSizeToTableSizeDto, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.tableSizeToTableSizeDto = tableSizeToTableSizeDto;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<TableSizeDto> findAll(PgCredentials pgCredentials) {
        try(HikariDataSource dataSource = getHikariDataSource(pgCredentials)) {
            TableSizeRepository tableSizeRepository = getTableSizeRepository(dataSource);
            return tableSizeRepository
                    .findAll()
                    .stream()
                    .map(tableSizeToTableSizeDto::convert)
                    .collect(Collectors.toList());
        }
    }

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected TableSizeRepository getTableSizeRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, TableSizeRepository.class);
    }

}
