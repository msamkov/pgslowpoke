package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.TableSizeToTableSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.repositories.TableSizeRepository;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TableSizeServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        TableSizeRepository tableSizeRepository =
                mock(TableSizeRepository.class);
        SizeFormatter sizeFormatter = new SizeFormatter();
        TableSizeToTableSizeDto tableSizeToTableSizeDto = new TableSizeToTableSizeDto(sizeFormatter);
        TableSizeService tableSizeService =
                new TableSizeService(myBatisMapperFactory, tableSizeToTableSizeDto, dataSourceFactory);

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
        when(tableSizeService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(tableSizeService.getTableSizeRepository(dataSource)).thenReturn(tableSizeRepository);

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
        List<TableSizeDto> actual = tableSizeService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}