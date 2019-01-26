package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;
import ru.multicon.pgslowpoke.services.dto.IndexSizeDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexSizeService {
    private final IndexSizeRepository indexSizeRepository;
    private final IndexSizeToIndexSizeDto indexSizeToIndexSizeDto;

    @Autowired
    public IndexSizeService(IndexSizeRepository indexSizeRepository, IndexSizeToIndexSizeDto indexSizeToIndexSizeDto) {
        this.indexSizeRepository = indexSizeRepository;
        this.indexSizeToIndexSizeDto = indexSizeToIndexSizeDto;
    }

    public List<IndexSizeDto> findAll() {
        return indexSizeRepository
                .findAll()
                .stream()
                .map(indexSizeToIndexSizeDto::convert)
                .collect(Collectors.toList());
    }
}
