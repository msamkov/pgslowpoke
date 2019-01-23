package ru.multicon.pgslowpoke.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PgStatUserTablesServiceTest {

    @Mock
    private PgStatUserTablesRepository pgStatUserTablesRepository =
        mock(PgStatUserTablesRepository.class, RETURNS_DEEP_STUBS);

    private PgStatUserTablesService pgStatUserTablesService;

    @Before
    public void setUp() {
        this.pgStatUserTablesService = new PgStatUserTablesService(pgStatUserTablesRepository);
    }

    @Test
    public void findAll() {
        final List<PgStatUserTables> statUserTables = Arrays.asList(
            new PgStatUserTables("s1", "t1", 11L, 12L, 13L, 14L, 15L, 16L, 17L),
            new PgStatUserTables("s2", "t2", 21L, 22L, 23L, 24L, 25L, 26L, 27L)
        );
        when(pgStatUserTablesRepository.findAll()).thenReturn(statUserTables);
        assertEquals(statUserTables, pgStatUserTablesService.findAll());
        verify(pgStatUserTablesRepository, only()).findAll();
    }
}