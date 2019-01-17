package ru.multicon.pgslowpoke.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ForeignKeyDuplicate {
    private String childTable;
    private String childColumn;
    private String parentTable;
    private String parentColumn;
    private String constraintName;
}
