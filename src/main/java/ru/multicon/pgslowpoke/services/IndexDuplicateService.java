package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;

import java.util.List;

@Service
public class IndexDuplicateService {
    private final IndexDuplicateRepository indexDuplicateRepository;

    @Autowired
    public IndexDuplicateService(IndexDuplicateRepository indexDuplicateRepository) {
        this.indexDuplicateRepository = indexDuplicateRepository;
    }

    public List<IndexDuplicate> findAll() {
        return indexDuplicateRepository.findAll();
    }
}
