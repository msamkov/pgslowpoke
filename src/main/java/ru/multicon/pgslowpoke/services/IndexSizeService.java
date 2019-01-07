package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexSize;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;

import java.util.List;

@Service
public class IndexSizeService {
    private final IndexSizeRepository indexSizeRepository;

    @Autowired
    public IndexSizeService(IndexSizeRepository indexSizeRepository) {
        this.indexSizeRepository = indexSizeRepository;
    }

    public List<IndexSize> findAll() {
        return indexSizeRepository.findAll();
    }
}
