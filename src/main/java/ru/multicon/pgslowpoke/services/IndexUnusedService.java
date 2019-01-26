package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexUnusedService {
    private final IndexUnusedRepository IndexUnusedRepository;
    private final IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto;

    @Autowired
    public IndexUnusedService(IndexUnusedRepository IndexUnusedRepository, IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto) {
        this.IndexUnusedRepository = IndexUnusedRepository;
        this.indexUnusedToIndexUnusedDto = indexUnusedToIndexUnusedDto;
    }

    public List<IndexUnusedDto> findAll() {
        return IndexUnusedRepository
                .findAll()
                .stream()
                .map(indexUnusedToIndexUnusedDto::convert)
                .collect(Collectors.toList());
    }
}