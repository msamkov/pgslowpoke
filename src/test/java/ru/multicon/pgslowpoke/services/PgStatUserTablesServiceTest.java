package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PgStatUserTablesServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgStatUserTablesRepository pgStatUserTablesRepository = mock(PgStatUserTablesRepository.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        PgStatUserTablesService pgStatUserTablesService =
                new PgStatUserTablesService(myBatisMapperFactory, dataSourceFactory);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        final List<PgStatUserTables> statUserTables = Arrays.asList(
            new PgStatUserTables("s1", "t1", 11L, 12L, 13L, 14L, 15L, 16L, 17L),
            new PgStatUserTables("s2", "t2", 21L, 22L, 23L, 24L, 25L, 26L, 27L)
        );
        final List<PgStatUserTables> expected = statUserTables;
        when(pgStatUserTablesService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(pgStatUserTablesService.getPgStatUserTablesRepository(dataSource)).thenReturn(pgStatUserTablesRepository);
        when(pgStatUserTablesRepository.findAll()).thenReturn(statUserTables);

        //выполняем
        List<PgStatUserTables> actual = pgStatUserTablesService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}
