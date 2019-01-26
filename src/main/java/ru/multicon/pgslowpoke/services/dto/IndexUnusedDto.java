package ru.multicon.pgslowpoke.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class IndexUnusedDto {
    private String schema;
    private String table;
    private String index;
    private String size;
}
