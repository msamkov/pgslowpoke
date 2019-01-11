package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexDuplicateServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        IndexDuplicateRepository indexDuplicateRepository =
                mock(IndexDuplicateRepository.class);
        IndexDuplicateService indexDuplicateService =
                new IndexDuplicateService(indexDuplicateRepository);

        List<IndexDuplicate> indexDuplicates = Arrays.asList(
                new IndexDuplicate()
                        .setSchema("s_test")
                        .setTable("t_duplicate_index")
                        .setIndex("t_duplicate_index_name_idx")
                        .setSql("CREATE INDEX t_duplicate_index_name_idx ON s_test.t_duplicate_index USING btree (name)")
                        .setPrimaryKey(true),
                new IndexDuplicate()
                        .setSchema("s_test")
                        .setTable("t_duplicate_index")
                        .setIndex("t_duplicate_index_name2_idx")
                        .setSql("CREATE INDEX t_duplicate_index_name2_idx ON s_test.t_duplicate_index USING btree (name)")
                        .setPrimaryKey(false)
        );

        when(indexDuplicateRepository.findAll()).thenReturn(indexDuplicates);
        List<IndexDuplicate> expected = indexDuplicates;

        //выполняем
        List<IndexDuplicate> actual = indexDuplicateService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}