package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexUnusedServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        IndexUnusedRepository indexUnusedRepository =
                mock(IndexUnusedRepository.class);
        IndexUnusedService indexUnusedService =
                new IndexUnusedService(indexUnusedRepository);

        List<IndexUnused> indexUnuseds = Arrays.asList(
                IndexUnused.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_name_idx")
                        .size("8192 bytes")
                        .build(),
                IndexUnused.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size("3008 kB")
                        .build()
        );

        when(indexUnusedRepository.findAll()).thenReturn(indexUnuseds);
        List<IndexUnused> expected = indexUnuseds;

        //выполняем
        List<IndexUnused> actual = indexUnusedService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}