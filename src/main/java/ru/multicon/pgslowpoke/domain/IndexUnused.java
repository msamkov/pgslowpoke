package ru.multicon.pgslowpoke.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class IndexUnused {
    private String schema;
    private String table;
    private String index;
    private long size;

}
