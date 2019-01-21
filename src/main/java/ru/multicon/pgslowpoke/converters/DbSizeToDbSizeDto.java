package ru.multicon.pgslowpoke.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.controllers.dto.DbSizeDto;
import ru.multicon.pgslowpoke.domain.DbSize;
import ru.multicon.pgslowpoke.utils.SizeFormatter;

@Component
public class DbSizeToDbSizeDto implements Converter<DbSize, DbSizeDto> {

    private final SizeFormatter sizeFormatter;

    @Autowired
    public DbSizeToDbSizeDto(SizeFormatter sizeFormatter) {
        this.sizeFormatter = sizeFormatter;
    }

    @Override
    public DbSizeDto convert(DbSize dbSize) {
        return DbSizeDto.builder()
                .name(dbSize.getName())
                .size(sizeFormatter.humanReadable(dbSize.getSize()))
                .build();
    }
}
