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
                IndexSize.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_pkey")
                        .size("8192 bytes")
                        .build(),
                IndexSize.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size("3008 kB")
                        .build()
                );

        when(indexSizeRepository.findAll()).thenReturn(indexSizes);
        List<IndexSize> expected = indexSizes;

        //выполняем
        List<IndexSize> actual = indexSizeService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}