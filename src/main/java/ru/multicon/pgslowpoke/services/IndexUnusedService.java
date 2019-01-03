package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;

import java.util.List;

@Service
public class IndexUnusedService {
    private final IndexUnusedRepository IndexUnusedRepository;

    @Autowired
    public IndexUnusedService(IndexUnusedRepository IndexUnusedRepository) {
        this.IndexUnusedRepository = IndexUnusedRepository;
    }

    public List<IndexUnused> findAll() {
        return IndexUnusedRepository.findAll();
    }
}