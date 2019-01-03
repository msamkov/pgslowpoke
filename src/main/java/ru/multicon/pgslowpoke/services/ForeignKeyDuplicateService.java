package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;

import java.util.List;

@Service
public class ForeignKeyDuplicateService {
    private final ForeignKeyDuplicateRepository foreignKeyDuplicateRepository;

    @Autowired
    public ForeignKeyDuplicateService(ForeignKeyDuplicateRepository foreignKeyDuplicateRepository) {
        this.foreignKeyDuplicateRepository = foreignKeyDuplicateRepository;
    }

    public List<ForeignKeyDuplicate> findAll() {
        return foreignKeyDuplicateRepository.findAll();
    }
}
