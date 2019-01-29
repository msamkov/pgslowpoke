package ru.multicon.pgslowpoke.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.multicon.pgslowpoke.domain.PgSettings;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import ru.multicon.pgslowpoke.repositories.PgSettingsRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PgSettingsServiceTest {

//    @Mock
//    private PgSettingsRepository pgSettingsRepository =
//        mock(PgSettingsRepository.class, RETURNS_DEEP_STUBS);
//
//    @Mock
//    private PgSettingsHintService pgSettingsHintService =
//        mock(PgSettingsHintService.class, RETURNS_DEEP_STUBS);
//
//    private PgSettingsService pgSettingsService;
//
//    private final List<PgSettings> settings = Arrays.asList(
//        new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", null),
//        new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", null),
//        new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", null)
//    );
//
//    private final List<PgSettingsHint> settingsHints = Arrays.asList(
//        new PgSettingsHint("n1", "d1", "h1"),
//        new PgSettingsHint("n3", null, "h3")
//    );
//
//    @Before
//    public void setUp() {
//        pgSettingsService = new PgSettingsService(pgSettingsRepository, pgSettingsHintService);
//    }
//
//    @Test
//    public void findAllEmpty() {
//        when(pgSettingsRepository.findAll()).thenReturn(Collections.emptyList());
//        when(pgSettingsHintService.findAll()).thenReturn(Collections.emptyList());
//        final List<PgSettings> result = pgSettingsService.findAll();
//        assertEquals(Collections.emptyList(), result);
//        verify(pgSettingsRepository, times(1)).findAll();
//        verify(pgSettingsHintService, times(1)).findAll();
//    }
//
//    @Test
//    public void findAll() {
//        when(pgSettingsRepository.findAll()).thenReturn(settings);
//        when(pgSettingsHintService.findAll()).thenReturn(settingsHints);
//        final List<PgSettings> result = pgSettingsService.findAll();
//        final List<PgSettings> expected = Arrays.asList(
//            new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", "d1"),
//            new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", null),
//            new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", null)
//        );
//        assertEquals(expected, result);
//        verify(pgSettingsRepository, times(1)).findAll();
//        verify(pgSettingsHintService, times(1)).findAll();
//    }
//
//    @Test
//    public void findPrimarySettingsEmpty() {
//        when(pgSettingsRepository.findPrimarySettings()).thenReturn(Collections.emptyList());
//        when(pgSettingsHintService.findAll()).thenReturn(Collections.emptyList());
//        final List<PgSettings> result = pgSettingsService.findPrimarySettings();
//        assertEquals(Collections.emptyList(), result);
//        verify(pgSettingsRepository, times(1)).findPrimarySettings();
//        verify(pgSettingsHintService, times(1)).findAll();
//    }
//
//    @Test
//    public void findPrimarySettings() {
//        when(pgSettingsRepository.findPrimarySettings()).thenReturn(settings);
//        when(pgSettingsHintService.findAll()).thenReturn(settingsHints);
//        final List<PgSettings> result = pgSettingsService.findPrimarySettings();
//        final List<PgSettings> expected = Arrays.asList(
//            new PgSettings("n1", "v1", "s1", "mn1", "mx1", "u1", "d1"),
//            new PgSettings("n2", "v2", "s2", "mn2", "mx2", "u2", null),
//            new PgSettings("n3", "v3", "s3", "mn3", "mx3", "u3", null)
//        );
//        assertEquals(expected, result);
//        verify(pgSettingsRepository, times(1)).findPrimarySettings();
//        verify(pgSettingsHintService, times(1)).findAll();
//    }
}
