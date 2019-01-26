package ru.multicon.pgslowpoke.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.converters.IndexSizeToIndexSizeDto;
import ru.multicon.pgslowpoke.domain.PgCredentials;
import ru.multicon.pgslowpoke.repositories.IndexSizeRepository;
import ru.multicon.pgslowpoke.services.dto.IndexSizeDto;
import ru.multicon.pgslowpoke.utils.MyBatisMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexSizeService {
    private final MyBatisMapper myBatisMapper;
    private final IndexSizeToIndexSizeDto indexSizeToIndexSizeDto;

    @Autowired
    public IndexSizeService(MyBatisMapper myBatisMapper, IndexSizeToIndexSizeDto indexSizeToIndexSizeDto) {
        this.myBatisMapper = myBatisMapper;
        this.indexSizeToIndexSizeDto = indexSizeToIndexSizeDto;
    }

    public List<IndexSizeDto> findAll(PgCredentials pgCredentials) {
        IndexSizeRepository indexSizeRepository = myBatisMapper.getMapper(pgCredentials, IndexSizeRepository.class);
        return indexSizeRepository
                .findAll()
                .stream()
                .map(indexSizeToIndexSizeDto::convert)
                .collect(Collectors.toList());
    }
}
