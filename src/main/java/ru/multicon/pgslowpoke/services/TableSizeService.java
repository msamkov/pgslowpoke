package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableSizeService {
    private final TableSizeRepository tableSizeRepository;
    private final TableSizeToTableSizeDto tableSizeToTableSizeDto;

    @Autowired
    public TableSizeService(TableSizeRepository tableSizeRepository, TableSizeToTableSizeDto tableSizeToTableSizeDto) {
        this.tableSizeRepository = tableSizeRepository;
        this.tableSizeToTableSizeDto = tableSizeToTableSizeDto;
    }

    public List<TableSizeDto> findAll() {
        return tableSizeRepository
                .findAll()
                .stream()
                .map(tableSizeToTableSizeDto::convert)
                .collect(Collectors.toList());
    }
}
