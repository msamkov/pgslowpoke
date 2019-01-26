package ru.multicon.pgslowpoke.converters;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import static org.junit.Assert.*;

public class TableSizeToTableSizeDtoTest {

    @Test
    public void convert() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        TableSizeToTableSizeDto tableSizeToTableSizeDto = new TableSizeToTableSizeDto(sizeFormatter);
        String schema = "s_test";
        String table = "t_user";
        long size = 32;
        String sizeHumanReadable = "32 bytes";

        TableSize tableSize = TableSize.builder()
                .schema(schema)
                .table(table)
                .size(size)
                .build();

        TableSizeDto expected = TableSizeDto.builder()
                .schema(schema)
                .table(table)
                .size(sizeHumanReadable)
                .build();

        //выполняем
        TableSizeDto actual = tableSizeToTableSizeDto.convert(tableSize);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}