package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;

import java.util.List;

@Service
public class TableSizeService {
    private final TableSizeRepository tableSizeRepository;

    @Autowired
    public TableSizeService(TableSizeRepository tableSizeRepository) {
        this.tableSizeRepository = tableSizeRepository;
    }

    public List<TableSize> findAll() {
        return tableSizeRepository.findAll();
    }
}
