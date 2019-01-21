package ru.multicon.pgslowpoke.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TableSizeDto {
    private String schema;
    private String table;
    private String size;
}
