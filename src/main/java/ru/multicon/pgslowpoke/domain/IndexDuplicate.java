package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IndexDuplicate {
    String table;
    String index;
    String sql;
    boolean isPrimaryKey;
}
