package ru.multicon.pgslowpoke.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.services.dto.TableSizeDto;
import ru.multicon.pgslowpoke.domain.TableSize;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

@Component
public class TableSizeToTableSizeDto implements Converter<TableSize, TableSizeDto> {

    private final SizeFormatter sizeFormatter;

    @Autowired
    public TableSizeToTableSizeDto(SizeFormatter sizeFormatter) {
        this.sizeFormatter = sizeFormatter;
    }

    @Override
    public TableSizeDto convert(TableSize tableSize) {
        return TableSizeDto.builder()
                .schema(tableSize.getSchema())
                .table(tableSize.getTable())
                .size(sizeFormatter.humanReadable(tableSize.getSize()))
                .build();
    }
}