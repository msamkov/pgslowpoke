package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;

@Service
public class PgStatUserTablesService {
    private final MyBatisMapperFactory myBatisMapperFactory;

    @Autowired
    public PgStatUserTablesService(MyBatisMapperFactory myBatisMapperFactory) {
        this.myBatisMapperFactory = myBatisMapperFactory;
    }

    public List<PgStatUserTables> findAll(PgCredentials pgCredentials) {
        PgStatUserTablesRepository pgStatUserTablesRepository =
                myBatisMapperFactory.create(pgCredentials, PgStatUserTablesRepository.class);
        return pgStatUserTablesRepository.findAll();
    }
}
