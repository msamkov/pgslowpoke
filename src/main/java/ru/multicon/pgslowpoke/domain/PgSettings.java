package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PgSettings {
    private String name;
    private String value; // текущее значение. Расчитывается как setting * unit
    private String setting;
    private String min;
    private String max;
    private String unit;
    private String description;
}
