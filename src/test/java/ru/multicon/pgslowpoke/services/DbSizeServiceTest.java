package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.DbSizeToDbSizeDto;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.repositories.DbSizeRepository;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DbSizeServiceTest {

//    @Test
//    public void findByName() {
//        //подготавливаем
//        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
//        SizeFormatter sizeFormatter = new SizeFormatter();
//        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
//        DbSizeService dbSizeService = new DbSizeService(dbSizeRepository, dbSizeToDbSizeDto, myBatisMapper);
//        String dbName = "test";
//        DbSize dbSize = DbSize.builder()
//                .name(dbName)
//                .size(10001)
//                .build();
//        when(dbSizeRepository.findByName(dbName)).thenReturn(dbSize);
//        DbSizeDto expected = DbSizeDto.builder()
//                .name(dbName)
//                .size("10001 bytes")
//                .build();
//
//        //выполняем
//        DbSizeDto actual = dbSizeService.findByName(dbName);
//
//        //сравниваем
//        Assert.assertEquals(expected, actual);
//    }

//    @Test
//    public void current() {
//        //подготавливаем
//        DbSizeRepository dbSizeRepository = mock(DbSizeRepository.class);
//        SizeFormatter sizeFormatter = new SizeFormatter();
//        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
//        DbSizeService dbSizeService = new DbSizeService(dbSizeRepository, dbSizeToDbSizeDto, myBatisMapper);
//        String dbName = "test";
//        DbSize dbSize = DbSize.builder()
//                .name(dbName)
//                .size(1001)
//                .build();
//        when(dbSizeRepository.current()).thenReturn(dbSize);
//        DbSizeDto expected = DbSizeDto.builder()
//                .name(dbName)
//                .size("1001 bytes")
//                .build();
//
//        //выполняем
//        DbSizeDto actual = dbSizeService.current();
//
//        //сравниваем
//        Assert.assertEquals(expected, actual);
//    }
}