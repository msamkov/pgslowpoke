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
                new IndexUnused()
                        .setSchema("s_test")
                        .setTable("t_user")
                        .setIndex("t_user_name_idx")
                        .setSize("8192 bytes"),
                new IndexUnused()
                        .setSchema("s_test")
                        .setTable("t_index_unused")
                        .setIndex("t_index_unused_name_idx")
                        .setSize("3008 kB")
        );

        when(indexUnusedRepository.findAll()).thenReturn(indexUnuseds);
        List<IndexUnused> expected = indexUnuseds;

        //выполняем
        List<IndexUnused> actual = indexUnusedService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}