package ru.multicon.pgslowpoke.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class IndexDuplicate {
    private String schema;
    private String table;
    private String index;
    private String sql;
    private boolean primaryKey;
}
