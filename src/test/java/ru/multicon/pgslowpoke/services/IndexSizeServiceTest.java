package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.domain.IndexSize;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;
import ru.multicon.pgslowpoke.services.dto.IndexSizeDto;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexSizeServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        IndexSizeRepository indexSizeRepository =
                mock(IndexSizeRepository.class);

        SizeFormatter sizeFormatter = new SizeFormatter();
        IndexSizeToIndexSizeDto indexSizeToIndexSizeDto = new IndexSizeToIndexSizeDto(sizeFormatter);
        IndexSizeService indexSizeService =
                new IndexSizeService(myBatisMapperFactory,
                        indexSizeToIndexSizeDto, dataSourceFactory);

        List<IndexSize> indexSizes = Arrays.asList(
                IndexSize.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_pkey")
                        .size(8192)
                        .build(),
                IndexSize.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size(3008)
                        .build()
                );
        when(indexSizeService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(indexSizeService.getIndexSizeRepository(dataSource)).thenReturn(indexSizeRepository);
        when(indexSizeRepository.findAll()).thenReturn(indexSizes);
        List<IndexSizeDto> expected = Arrays.asList(
                IndexSizeDto.builder()
                        .schema("s_test")
                        .table("t_user")
                        .index("t_user_pkey")
                        .size("8192 bytes")
                        .build(),
                IndexSizeDto.builder()
                        .schema("s_test")
                        .table("t_index_unused")
                        .index("t_index_unused_name_idx")
                        .size("3008 bytes")
                        .build()
        );

        //выполняем
        List<IndexSizeDto> actual = indexSizeService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}