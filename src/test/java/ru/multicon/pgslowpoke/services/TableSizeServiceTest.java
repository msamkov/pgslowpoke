package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableSizeServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        TableSizeRepository tableSizeRepository =
                mock(TableSizeRepository.class);
        SizeFormatter sizeFormatter = new SizeFormatter();
        TableSizeToTableSizeDto tableSizeToTableSizeDto = new TableSizeToTableSizeDto(sizeFormatter);
        TableSizeService TableSizeService =
                new TableSizeService(tableSizeRepository, tableSizeToTableSizeDto);

        List<TableSize> tableSizes = Arrays.asList(
                TableSize.builder()
                        .schema("s_test")
                        .table("t_user")
                        .size(8192)
                        .build(),
                TableSize.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .size(3008)
                        .build()
        );

        when(tableSizeRepository.findAll()).thenReturn(tableSizes);
        List<TableSizeDto> expected = Arrays.asList(
                TableSizeDto.builder()
                        .schema("s_test")
                        .table("t_user")
                        .size("8192 bytes")
                        .build(),
                TableSizeDto.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .size("3008 bytes")
                        .build()
        );

        //выполняем
        List<TableSizeDto> actual = TableSizeService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}