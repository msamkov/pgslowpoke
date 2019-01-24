package ru.multicon.pgslowpoke.converters;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.controllers.dto.DbSizeDto;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.utils.SizeFormatter;


public class DbSizeToDbSizeDtoTest {

    @Test
    public void convert() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
        String dbName = "dbtest";
        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(30)
                .build();
        DbSizeDto expected = DbSizeDto.builder()
                .name(dbName)
                .size("30 bytes")
                .build();

        //выполняем
        DbSizeDto actual = dbSizeToDbSizeDto.convert(dbSize);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}