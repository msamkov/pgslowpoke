package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;
import ru.multicon.pgslowpoke.repositories.PgStatUserTablesRepository;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;

@Service
public class PgStatUserTablesService {
    private final MyBatisMapper myBatisMapper;

    @Autowired
    public PgStatUserTablesService(MyBatisMapper myBatisMapper) {
        this.myBatisMapper = myBatisMapper;
    }

    public List<PgStatUserTables> findAll(PgCredentials pgCredentials) {
        PgStatUserTablesRepository pgStatUserTablesRepository =
                myBatisMapper.getMapper(pgCredentials, PgStatUserTablesRepository.class);
        return pgStatUserTablesRepository.findAll();
    }
}
