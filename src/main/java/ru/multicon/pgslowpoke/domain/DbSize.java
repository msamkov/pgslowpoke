package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DbSize {
    String name;
    String size;
}
