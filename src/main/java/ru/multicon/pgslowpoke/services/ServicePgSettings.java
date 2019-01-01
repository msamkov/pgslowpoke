package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.repositories.RepositoryPgSettings;
import java.util.List;

@Service
public class ServicePgSettings {

    private final RepositoryPgSettings repositoryPgSettings;

    @Autowired
    public ServicePgSettings(RepositoryPgSettings repositoryPgSettings) {
        this.repositoryPgSettings = repositoryPgSettings;
    }

    public List<PgSettings> findAll() {
        return repositoryPgSettings.findAll();
    }

    public List<PgSettings> findPrimarySettings() {
        return repositoryPgSettings.findPrimarySettings();

    }
}
