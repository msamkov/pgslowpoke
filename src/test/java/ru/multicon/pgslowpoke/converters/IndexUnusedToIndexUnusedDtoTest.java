package ru.multicon.pgslowpoke.converters;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import static org.junit.Assert.*;

public class IndexUnusedToIndexUnusedDtoTest {

    @Test
    public void convert() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto = new IndexUnusedToIndexUnusedDto(sizeFormatter);
        String schema = "s_test";
        String table = "t_user";
        String index = "t_user_name_idx";
        long size = 32;
        String sizeHumanReadable = "32 bytes";

        IndexUnused indexUnused = IndexUnused.builder()
                .schema(schema)
                .table(table)
                .index(index)
                .size(size)
                .build();

        IndexUnusedDto expected = IndexUnusedDto.builder()
                .schema(schema)
                .table(table)
                .index(index)
                .size(sizeHumanReadable)
                .build();

        //выполняем
        IndexUnusedDto actual = indexUnusedToIndexUnusedDto.convert(indexUnused);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}