package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;

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
        TableSizeService TableSizeService =
                new TableSizeService(tableSizeRepository);

        List<TableSize> tableSizes = Arrays.asList(
                new TableSize()
                        .setSchema("s_test")
                        .setTable("t_user")
                        .setSize("8192 bytes"),
                new TableSize()
                        .setSchema("s_test")
                        .setTable("t_index_unused")
                        .setSize("3008 kB")
        );

        when(tableSizeRepository.findAll()).thenReturn(tableSizes);
        List<TableSize> expected = tableSizes;

        //выполняем
        List<TableSize> actual = TableSizeService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}