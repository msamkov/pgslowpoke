package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IndexDuplicate {
    private String schema;
    private String table;
    private String index;
    private String sql;
    private boolean primaryKey;
}
