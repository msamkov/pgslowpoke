package ru.multicon.pgslowpoke.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class TableSizeDto {
    private String schema;
    private String table;
    private String size;
}
