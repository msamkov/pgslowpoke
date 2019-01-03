package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IndexUnused {
    private String schema;
    private String table;
    private String index;
    private String size;

}
