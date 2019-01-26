package ru.multicon.pgslowpoke.converters;

import org.junit.Assert;
import org.junit.Test;

import ru.multicon.pgslowpoke.domain.IndexSize;
import ru.multicon.pgslowpoke.services.dto.IndexSizeDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;



public class IndexSizeToIndexSizeDtoTest {

    @Test
    public void convert() {
        //подготавливаем
        SizeFormatter sizeFormatter = new SizeFormatter();
        IndexSizeToIndexSizeDto IndexSizeToIndexSizeDto = new IndexSizeToIndexSizeDto(sizeFormatter);
        String schema = "s_test";
        String table = "t_user";
        String index = "t_user_name_idx";
        long size = 32;
        String sizeHumanReadable = "32 bytes";

        IndexSize indexSize = IndexSize.builder()
                .schema(schema)
                .table(table)
                .index(index)
                .size(size)
                .build();

        IndexSizeDto expected = IndexSizeDto.builder()
                .schema(schema)
                .table(table)
                .index(index)
                .size(sizeHumanReadable)
                .build();

        //выполняем
        IndexSizeDto actual = IndexSizeToIndexSizeDto.convert(indexSize);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}