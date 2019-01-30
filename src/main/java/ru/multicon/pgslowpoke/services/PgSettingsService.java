package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.repositories.PgSettingsRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PgSettingsService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final PgSettingsHintService servicePgSettingsHint;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public PgSettingsService(MyBatisMapperFactory myBatisMapperFactory, PgSettingsHintService servicePgSettingsHint, DataSourceFactory dataSourceFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.servicePgSettingsHint = servicePgSettingsHint;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<PgSettings> findAll(PgCredentials pgCredentials) {
        HikariDataSource dataSource = getHikariDataSource(pgCredentials);
        PgSettingsRepository pgSettingsRepository = getPgSettingsRepository(dataSource);
        List<PgSettings> pgSettings = joinDescription(
            pgSettingsRepository.findAll(),
            servicePgSettingsHint.findAll()
        );
        dataSource.close();
        return pgSettings;
    }

    public List<PgSettings> findPrimarySettings(PgCredentials pgCredentials) {
        HikariDataSource dataSource = getHikariDataSource(pgCredentials);
        PgSettingsRepository pgSettingsRepository = getPgSettingsRepository(dataSource);
        List<PgSettings> pgSettings = joinDescription(
            pgSettingsRepository.findPrimarySettings(),
            servicePgSettingsHint.findAll()
        );
        dataSource.close();
        return pgSettings;
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

    protected HikariDataSource getHikariDataSource(PgCredentials pgCredentials) {
        return dataSourceFactory.create(pgCredentials);
    }

    protected PgSettingsRepository getPgSettingsRepository(HikariDataSource dataSource) {
        return myBatisMapperFactory.create(dataSource, PgSettingsRepository.class);
    }

}
