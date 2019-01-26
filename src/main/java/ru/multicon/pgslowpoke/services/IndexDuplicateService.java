package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;

@Service
public class IndexDuplicateService {
    private final MyBatisMapper myBatisMapper;

    @Autowired
    public IndexDuplicateService(MyBatisMapper myBatisMapper) {
        this.myBatisMapper = myBatisMapper;
    }

    public List<IndexDuplicate> findAll(PgCredentials pgCredentials) {
        IndexDuplicateRepository indexDuplicateRepository =
                myBatisMapper.getMapper(pgCredentials, IndexDuplicateRepository.class);
        return indexDuplicateRepository.findAll();
    }
}
