package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexUnusedToIndexUnusedDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexUnusedRepository;
import ru.multicon.pgslowpoke.services.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexUnusedService {
    private final MyBatisMapper myBatisMapper;
    private final IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto;

    @Autowired
    public IndexUnusedService(MyBatisMapper myBatisMapper, IndexUnusedToIndexUnusedDto indexUnusedToIndexUnusedDto) {
        this.myBatisMapper = myBatisMapper;
        this.indexUnusedToIndexUnusedDto = indexUnusedToIndexUnusedDto;
    }

    public List<IndexUnusedDto> findAll(PgCredentials pgCredentials) {
        IndexUnusedRepository indexUnusedRepository =
                myBatisMapper.getMapper(pgCredentials, IndexUnusedRepository.class);
        return indexUnusedRepository
                .findAll()
                .stream()
                .map(indexUnusedToIndexUnusedDto::convert)
                .collect(Collectors.toList());
    }
}