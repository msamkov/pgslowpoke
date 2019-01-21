package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DbSizeServiceTest {

    @Test
    public void findByName() {
        //подготавливаем
        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
        DbSizeService dbSizeService = new DbSizeService(dbSizeRepository);
        String dbName = "test";
        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(10001)
                .build();
        when(dbSizeRepository.findByName(dbName)).thenReturn(dbSize);
        DbSize expected = dbSize;

        //выполняем
        DbSize actual = dbSizeService.findByName(dbName);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void current() {
        //подготавливаем
        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
        DbSizeService dbSizeService = new DbSizeService(dbSizeRepository);
        String dbName = "test";
        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(1001)
                .build();
        when(dbSizeRepository.current()).thenReturn(dbSize);
        DbSize expected = dbSize;

        //выполняем
        DbSize actual = dbSizeService.current();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}