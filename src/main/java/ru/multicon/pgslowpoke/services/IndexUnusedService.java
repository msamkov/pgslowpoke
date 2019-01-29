package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapperFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexUnusedService {
    private final MyBatisMapperFactory myBatisMapperFactory;
    private final IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto;

    @Autowired
    public IndexUnusedService(MyBatisMapperFactory myBatisMapperFactory, IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto) {
        this.myBatisMapperFactory = myBatisMapperFactory;
        this.indexUnusedToIndexUnusedDto = indexUnusedToIndexUnusedDto;
    }

    public List<IndexUnusedDto> findAll(PgCredentials pgCredentials) {
        IndexUnusedRepository indexUnusedRepository =
                myBatisMapperFactory.create(pgCredentials, IndexUnusedRepository.class);
        return indexUnusedRepository
                .findAll()
                .stream()
                .map(indexUnusedToIndexUnusedDto::convert)
                .collect(Collectors.toList());
    }
}