package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ForeignKeyDuplicate {
    String childTable;
    String childColumn;
    String parentTable;
    String parentColumn;
    String constraintName;
}
