package ru.multicon.pgslowpoke.services;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.utils.DataSourceFactory;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForeignKeyDuplicateServiceTest {




    @Test
    public void findAll() {
        //подготавливаем
        MyBatisMapperFactory myBatisMapperFactory = mock(MyBatisMapperFactory.class);
        PgCredentials pgCredentials = mock(PgCredentials.class);
        DataSourceFactory dataSourceFactory = mock(DataSourceFactory.class);
        ForeignKeyDuplicateRepository foreignKeyDuplicateRepository = mock(ForeignKeyDuplicateRepository.class);
        ForeignKeyDuplicateService foreignKeyDuplicateService =
                new ForeignKeyDuplicateService(myBatisMapperFactory, dataSourceFactory);

        List<ForeignKeyDuplicate> foreignKeyDuplicates = Arrays.asList(
                ForeignKeyDuplicate.builder()
                        .childTable("t_foreign_key_duplicate")
                        .childColumn("user_id")
                        .parentTable("t_user")
                        .parentColumn("id")
                        .constraintName("t_foreign_key_duplicate_t_user_fkey")
                        .build(),
                ForeignKeyDuplicate.builder()
                        .childTable("t_foreign_key_duplicate")
                        .childColumn("user_id")
                        .parentTable("t_user")
                        .parentColumn("id")
                        .constraintName("t_foreign_key_duplicate_t_user_fkey2")
                        .build()
        );
        HikariDataSource dataSource = mock(HikariDataSource.class);
        when(foreignKeyDuplicateService.getHikariDataSource(pgCredentials)).thenReturn(dataSource);
        when(foreignKeyDuplicateService.getForeignKeyDuplicateRepository(dataSource))
                .thenReturn(foreignKeyDuplicateRepository);
        when(foreignKeyDuplicateRepository.findAll()).thenReturn(foreignKeyDuplicates);
        List<ForeignKeyDuplicate> expected = foreignKeyDuplicates;

        //выполняем
        List<ForeignKeyDuplicate> actual = foreignKeyDuplicateService.findAll(pgCredentials);

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}