package ru.multicon.pgslowpoke.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.controllers.dto.IndexUnusedDto;
import ru.multicon.pgslowpoke.domain.IndexUnused;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

@Component
public class IndexUnusedToIndexUnusedDto implements Converter<IndexUnused, IndexUnusedDto> {

    private final SizeFormatter sizeFormatter;

    @Autowired
    public IndexUnusedToIndexUnusedDto(SizeFormatter sizeFormatter) {
        this.sizeFormatter = sizeFormatter;
    }

    @Override
    public IndexUnusedDto convert(IndexUnused indexUnused) {
        return IndexUnusedDto.builder()
                .schema(indexUnused.getSchema())
                .table(indexUnused.getTable())
                .index(indexUnused.getIndex())
                .size(sizeFormatter.humanReadable(indexUnused.getSize()))
                .build();
    }
}