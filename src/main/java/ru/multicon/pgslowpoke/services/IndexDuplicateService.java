package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexDuplicateRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class IndexDuplicateService {
    private final MyBatisMapperFactory myBatisMapperFactory;

    @Autowired
    public IndexDuplicateService(MyBatisMapperFactory myBatisMapperFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
    }

    public List<IndexDuplicate> findAll(PgCredentials pgCredentials) {
        IndexDuplicateRepository indexDuplicateRepository =
                myBatisMapperFactory.create(pgCredentials, IndexDuplicateRepository.class);
        return indexDuplicateRepository.findAll();
    }
}
