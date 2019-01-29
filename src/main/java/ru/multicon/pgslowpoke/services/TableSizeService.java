package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableSizeService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final TableSizeToTableSizeDto tableSizeToTableSizeDto;

    @Autowired
    public TableSizeService(MyBatisMapperFactory myBatisMapperFactory, TableSizeToTableSizeDto tableSizeToTableSizeDto) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.tableSizeToTableSizeDto = tableSizeToTableSizeDto;
    }

    public List<TableSizeDto> findAll(PgCredentials pgCredentials) {
        TableSizeRepository tableSizeRepository =
                myBatisMapperFactory.create(pgCredentials, TableSizeRepository.class);
        return tableSizeRepository
                .findAll()
                .stream()
                .map(tableSizeToTableSizeDto::convert)
                .collect(Collectors.toList());
    }
}
