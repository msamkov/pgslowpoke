package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.repositories.RepositoryIndexDuplicate;

import java.util.List;

@Service
public class ServiceIndexDuplicate {
    private final RepositoryIndexDuplicate repositoryIndexDuplicate;

    @Autowired
    public ServiceIndexDuplicate(RepositoryIndexDuplicate repositoryIndexDuplicate) {
        this.repositoryIndexDuplicate = repositoryIndexDuplicate;
    }

    public List<IndexDuplicate> findAll() {
        return repositoryIndexDuplicate.findAll();
    }
}
