package ru.multicon.pgslowpoke.converters;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.services.dto.DbSizeDto;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.utils.SizeFormatter;


public class DbSizeToDbSizeDtoTest {

    @Test
    public void convert() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        DbSizeToDbSizeDto dbSizeToDbSizeDto = new DbSizeToDbSizeDto(sizeFormatter);
        String dbName = "dbtest";
        long size = 32;
        String sizeHumanReadable = "32 bytes";

        DbSize dbSize = DbSize.builder()
                .name(dbName)
                .size(size)
                .build();
        DbSizeDto expected = DbSizeDto.builder()
                .name(dbName)
                .size(sizeHumanReadable)
                .build();

        //выполняем
        DbSizeDto actual = dbSizeToDbSizeDto.convert(dbSize);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}