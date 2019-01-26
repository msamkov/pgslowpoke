package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.ForeignKeyDuplicateRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;

@Service
public class ForeignKeyDuplicateService {
    private final MyBatisMapper myBatisMapper;

    @Autowired
    public ForeignKeyDuplicateService(MyBatisMapper myBatisMapper) {
        this.myBatisMapper = myBatisMapper;
    }

    public List<ForeignKeyDuplicate> findAll(PgCredentials pgCredentials) {
        ForeignKeyDuplicateRepository foreignKeyDuplicateRepository = myBatisMapper.getMapper(pgCredentials,
                ForeignKeyDuplicateRepository.class);
        return foreignKeyDuplicateRepository.findAll();
    }
}
