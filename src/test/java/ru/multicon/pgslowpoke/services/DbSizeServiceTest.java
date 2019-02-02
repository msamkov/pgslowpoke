package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import static org.mockito.Mockito.*;

public class DbSizeServiceTest {

    @Test
    public void findByName() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        SizeFormatter sizeFormatter = new SizeFormatter();
        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
        DbSizeService dbSizeService = new DbSizeService(dbSizeToDbSizeDto, myBatisMapperFactory, dataSourceFactory);
        String dbName = "test";
        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(10001)
                .build();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        when(dbSizeService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(dbSizeService.getDbSizeRepository(dataSource)).thenReturn(dbSizeRepository);
        when(dbSizeRepository.findByName(dbName)).thenReturn(dbSize);
        DbSizeDto expected = DbSizeDto.builder()
                .name(dbName)
                .size("10001 bytes")
                .build();

        //выполняем
        DbSizeDto actual = dbSizeService.findByName(pgCredentials,dbName);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void current() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        SizeFormatter sizeFormatter = new SizeFormatter();
        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
        DbSizeService dbSizeService = new DbSizeService(dbSizeToDbSizeDto, myBatisMapperFactory, dataSourceFactory);
        String dbName = "test";
        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(1001)
                .build();
        HikariDataSource dataSource = mock(HikariDataSource.class);
        when(dbSizeService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(dbSizeService.getDbSizeRepository(dataSource)).thenReturn(dbSizeRepository);
        when(dbSizeRepository.current()).thenReturn(dbSize);
        DbSizeDto expected = DbSizeDto.builder()
                .name(dbName)
                .size("1001 bytes")
                .build();

        //выполняем
        DbSizeDto actual = dbSizeService.current(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}