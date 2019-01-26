package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

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
        SizeFormatter sizeFormatter = new SizeFormatter();
        IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto = new IndexUnusedToIndexUnusedDto(sizeFormatter);
        IndexUnusedService indexUnusedService =
                new IndexUnusedService(indexUnusedRepository, indexUnusedToIndexUnusedDto);

        List<IndexUnused> indexUnuseds = Arrays.asList(
                IndexUnused.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_name_idx")
                        .size(8192)
                        .build(),
                IndexUnused.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size(3008)
                        .build()
        );

        when(indexUnusedRepository.findAll()).thenReturn(indexUnuseds);
        List<IndexUnusedDto> expected = Arrays.asList(
                IndexUnusedDto.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_name_idx")
                        .size("8192 bytes")
                        .build(),
                IndexUnusedDto.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size("3008 bytes")
                        .build()
        );

        //выполняем
        List<IndexUnusedDto> actual = indexUnusedService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}