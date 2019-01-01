package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ForeignKeyDuplicate {
    private String childTable;
    private String childColumn;
    private String parentTable;
    private String parentColumn;
    private String constraintName;
}
