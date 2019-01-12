package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.IndexSize;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexSizeServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        IndexSizeRepository indexSizeRepository =
                mock(IndexSizeRepository.class);
        IndexSizeService indexSizeService =
                new IndexSizeService(indexSizeRepository);

        List<IndexSize> indexSizes = Arrays.asList(
                new IndexSize()
                        .setSchema("s_test")
                        .setTable("t_user")
                        .setIndex("t_user_pkey")
                        .setSize("8192 bytes"),
                new IndexSize()
                        .setSchema("s_test")
                        .setTable("t_index_unused")
                        .setIndex("t_index_unused_name_idx")
                        .setSize("3008 kB")
                );

        when(indexSizeRepository.findAll()).thenReturn(indexSizes);
        List<IndexSize> expected = indexSizes;

        //выполняем
        List<IndexSize> actual = indexSizeService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}