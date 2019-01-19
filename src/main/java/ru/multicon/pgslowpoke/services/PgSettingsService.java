package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.PgSettingsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PgSettingsService {

    private final PgSettingsHintService servicePgSettingsHint;

    private final PgSettingsRepository pgSettingsRepository;

    @Autowired
    public PgSettingsService(PgSettingsRepository pgSettingsRepository,
                             PgSettingsHintService servicePgSettingsHint) {
        this.pgSettingsRepository = pgSettingsRepository;
        this.servicePgSettingsHint = servicePgSettingsHint;
    }

    public List<PgSettings> findAll() {
        return joinDescription(pgSettingsRepository.findAll(),
                servicePgSettingsHint.findAll());
    }

    public List<PgSettings> findPrimarySettings() {
        return joinDescription(pgSettingsRepository.findPrimarySettings(),
                servicePgSettingsHint.findAll());
    }

    private List<PgSettings> joinDescription(final List<PgSettings> pgSettings,
                                             final List<PgSettingsHint> pgSettingsHints) {
        List<PgSettings> result = new ArrayList();
        for(PgSettings setting: pgSettings) {
            PgSettingsHint desc = pgSettingsHints
                    .stream()
                    .filter(s -> s.getName().equals(setting.getName()))
                    .findFirst()
                    .orElse(null);

            if(desc != null) {
                PgSettings pgSettingsDesc = PgSettings.builder()
                        .name(setting.getName())
                        .value(setting.getValue())
                        .setting(setting.getSetting())
                        .min(setting.getMin())
                        .max(setting.getMax())
                        .unit(setting.getUnit())
                        .description(desc.getDescription())
                        .build();
                result.add(pgSettingsDesc);
            }else {
                result.add(setting);
            }


        }
        return result;
    }
}
