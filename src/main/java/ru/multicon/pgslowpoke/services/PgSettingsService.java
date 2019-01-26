package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.PgSettingsRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PgSettingsService {
    private final MyBatisMapper myBatisMapper;
    private final PgSettingsHintService servicePgSettingsHint;

    @Autowired
    public PgSettingsService(MyBatisMapper myBatisMapper, PgSettingsHintService servicePgSettingsHint) {
        this.myBatisMapper = myBatisMapper;
        this.servicePgSettingsHint = servicePgSettingsHint;
    }

    public List<PgSettings> findAll(PgCredentials pgCredentials) {
        PgSettingsRepository pgSettingsRepository =
                myBatisMapper.getMapper(pgCredentials, PgSettingsRepository.class);
        return joinDescription(
            pgSettingsRepository.findAll(),
            servicePgSettingsHint.findAll()
        );
    }

    public List<PgSettings> findPrimarySettings(PgCredentials pgCredentials) {
        PgSettingsRepository pgSettingsRepository =
                myBatisMapper.getMapper(pgCredentials, PgSettingsRepository.class);
        return joinDescription(
            pgSettingsRepository.findPrimarySettings(),
            servicePgSettingsHint.findAll()
        );
    }

    private List<PgSettings> joinDescription(final List<PgSettings> pgSettings,
                                             final List<PgSettingsHint> pgSettingsHints) {
        return pgSettings.stream()
            .map(stn -> stn.withDescription(findDescription(stn.getName(), pgSettingsHints)))
            .collect(Collectors.toList());
    }

    private String findDescription(final String name, final List<PgSettingsHint> pgSettingsHints) {
        return pgSettingsHints.stream()
            .filter(hint -> hint.getName().equals(name))
            .findFirst()
            .map(PgSettingsHint::getDescription)
            .orElse(null);
    }
}
