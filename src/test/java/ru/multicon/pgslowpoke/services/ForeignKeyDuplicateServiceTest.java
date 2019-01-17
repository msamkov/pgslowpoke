package ru.multicon.pgslowpoke.services;

import org.junit.Assert;
import org.junit.Test;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForeignKeyDuplicateServiceTest {

    @Test
    public void findAll() {
        //подготавливаем
        ForeignKeyDuplicateRepository foreignKeyDuplicateRepository = mock(ForeignKeyDuplicateRepository.class);
        ForeignKeyDuplicateService foreignKeyDuplicateService =
                new ForeignKeyDuplicateService(foreignKeyDuplicateRepository);

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

        when(foreignKeyDuplicateRepository.findAll()).thenReturn(foreignKeyDuplicates);
        List<ForeignKeyDuplicate> expected = foreignKeyDuplicates;

        //выполняем
        List<ForeignKeyDuplicate> actual = foreignKeyDuplicateService.findAll();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }
}