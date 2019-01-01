package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.RepositoryPgSettings;
import java.util.List;

@Service
public class ServicePgSettings {

    private final ServicePgSettingsHint servicePgSettingsHint;

    private final RepositoryPgSettings repositoryPgSettings;

    @Autowired
    public ServicePgSettings(RepositoryPgSettings repositoryPgSettings,
                             ServicePgSettingsHint servicePgSettingsHint) {
        this.repositoryPgSettings = repositoryPgSettings;
        this.servicePgSettingsHint = servicePgSettingsHint;
    }

    public List<PgSettings> findAll() {
        return joinDescription(repositoryPgSettings.findAll(),
                servicePgSettingsHint.findAll());
    }

    public List<PgSettings> findPrimarySettings() {
        return joinDescription(repositoryPgSettings.findPrimarySettings(),
                servicePgSettingsHint.findAll());
    }

    private List<PgSettings> joinDescription(List<PgSettings> pgSettings,
                                             List<PgSettingsHint> pgSettingsHints) {
        for(PgSettings setting: pgSettings) {
            PgSettingsHint desc = pgSettingsHints
                    .stream()
                    .filter(s -> s.getName().equals(setting.getName()))
                    .findFirst()
                    .orElse(null);

            if(desc != null) {
                setting.setDescription(desc.getDescription());
            }
        }
        return pgSettings;
    }
}
