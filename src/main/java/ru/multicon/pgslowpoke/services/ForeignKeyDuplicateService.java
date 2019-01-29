package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class ForeignKeyDuplicateService {
    private final MyBatisMapperFactory myBatisMapperFactory;

    @Autowired
    public ForeignKeyDuplicateService(MyBatisMapperFactory myBatisMapperFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
    }

    public List<ForeignKeyDuplicate> findAll(PgCredentials pgCredentials) {
        ForeignKeyDuplicateRepository foreignKeyDuplicateRepository = myBatisMapperFactory.create(pgCredentials,
                ForeignKeyDuplicateRepository.class);
        return foreignKeyDuplicateRepository.findAll();
    }
}
