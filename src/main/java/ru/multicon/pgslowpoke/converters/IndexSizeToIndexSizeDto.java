package ru.multicon.pgslowpoke.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.controllers.dto.IndexSizeDto;
import ru.multicon.pgslowpoke.domain.IndexSize;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

@Component
public class IndexSizeToIndexSizeDto implements Converter<IndexSize, IndexSizeDto> {

    private final SizeFormatter sizeFormatter;

    @Autowired
    public IndexSizeToIndexSizeDto(SizeFormatter sizeFormatter) {
        this.sizeFormatter = sizeFormatter;
    }

    @Override
    public IndexSizeDto convert(IndexSize dbSize) {
        return IndexSizeDto.builder()
                .schema(dbSize.getSchema())
                .table(dbSize.getTable())
                .index(dbSize.getIndex())
                .size(sizeFormatter.humanReadable(dbSize.getSize()))
                .build();
    }
}