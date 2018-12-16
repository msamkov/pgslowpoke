package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.repositories.RepositoryForeignKeyDuplicate;

import java.util.List;

@Service
public class ServiceForeignKeyDuplicate {
    private final RepositoryForeignKeyDuplicate repositoryForeignKeyDuplicate;

    @Autowired
    public ServiceForeignKeyDuplicate(RepositoryForeignKeyDuplicate repositoryForeignKeyDuplicate) {
        this.repositoryForeignKeyDuplicate = repositoryForeignKeyDuplicate;
    }

    public List<ForeignKeyDuplicate> findAll() {
        return repositoryForeignKeyDuplicate.findAll();
    }
}
