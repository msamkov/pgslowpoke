package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexDuplicateServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        HikariDataSource dataSource = mock(HikariDataSource.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        IndexDuplicateRepository indexDuplicateRepository =
                mock(IndexDuplicateRepository.class);
        IndexDuplicateService indexDuplicateService =
                new IndexDuplicateService(myBatisMapperFactory, dataSourceFactory);

        List<IndexDuplicate> indexDuplicates = Arrays.asList(
                IndexDuplicate.builder()
                        .schema("s_test")
                        .table("t_duplicate_index")
                        .index("t_duplicate_index_name_idx")
                        .sql("CREATE INDEX t_duplicate_index_name_idx ON s_test.t_duplicate_index USING btree (name)")
                        .primaryKey(true)
                        .build(),
                IndexDuplicate.builder()
                        .schema("s_test")
                        .table("t_duplicate_index")
                        .index("t_duplicate_index_name2_idx")
                        .sql("CREATE INDEX t_duplicate_index_name2_idx ON s_test.t_duplicate_index USING btree (name)")
                        .primaryKey(false)
                        .build()
        );
        when(indexDuplicateService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(indexDuplicateService.getIndexDuplicateRepository(dataSource)).thenReturn(indexDuplicateRepository);
        when(indexDuplicateRepository.findAll()).thenReturn(indexDuplicates);
        List<IndexDuplicate> expected = indexDuplicates;

        //выполняем
        List<IndexDuplicate> actual = indexDuplicateService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}