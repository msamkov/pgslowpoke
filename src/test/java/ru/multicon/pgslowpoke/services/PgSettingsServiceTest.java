package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.PgSettingsRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PgSettingsServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        PgSettingsHintService pgSettingsHintService = mock(PgSettingsHintService.class);
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgSettingsRepository pgSettingsRepository = mock(PgSettingsRepository.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        PgSettingsService pgSettingsService =
                new PgSettingsService(myBatisMapperFactory, pgSettingsHintService, dataSourceFactory);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        final List<PgSettings> pgSettings = Arrays.asList(
                new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", null),
                new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", null),
                new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", null));

        String desc1 = "desc 1";
        String desc2 = "desc 2";
        String desc3 = "desc 3";
        final List<PgSettings> expected = Arrays.asList(
                new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", desc1),
                new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", desc2),
                new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", desc3));


        final List<PgSettingsHint> pgSettingsHints = Arrays.asList(
                new PgSettingsHint("n1", desc1, null),
                new PgSettingsHint("n2", desc2, null),
                new PgSettingsHint("n3", desc3, null));

        when(pgSettingsService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(pgSettingsService.getPgSettingsRepository(dataSource)).thenReturn(pgSettingsRepository);
        when(pgSettingsRepository.findAll()).thenReturn(pgSettings);
        when(pgSettingsHintService.findAll()).thenReturn(pgSettingsHints);

        //выполняем
        List<PgSettings> actual = pgSettingsService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);

    }


    @Test
    public void findPrimarySettings() {
        //подготавливаем
        PgSettingsHintService pgSettingsHintService = mock(PgSettingsHintService.class);
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgSettingsRepository pgSettingsRepository = mock(PgSettingsRepository.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        PgSettingsService pgSettingsService =
                new PgSettingsService(myBatisMapperFactory, pgSettingsHintService, dataSourceFactory);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        final List<PgSettings> pgSettings = Arrays.asList(
                new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", null),
                new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", null),
                new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", null));

        String desc1 = "desc 1";
        String desc2 = "desc 2";
        String desc3 = "desc 3";
        final List<PgSettings> expected = Arrays.asList(
                new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", desc1),
                new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", desc2),
                new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", desc3));


        final List<PgSettingsHint> pgSettingsHints = Arrays.asList(
                new PgSettingsHint("n1", desc1, null),
                new PgSettingsHint("n2", desc2, null),
                new PgSettingsHint("n3", desc3, null));

        when(pgSettingsService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(pgSettingsService.getPgSettingsRepository(dataSource)).thenReturn(pgSettingsRepository);
        when(pgSettingsRepository.findPrimarySettings()).thenReturn(pgSettings);
        when(pgSettingsHintService.findAll()).thenReturn(pgSettingsHints);

        //выполняем
        List<PgSettings> actual = pgSettingsService.findPrimarySettings(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);

    }
}
