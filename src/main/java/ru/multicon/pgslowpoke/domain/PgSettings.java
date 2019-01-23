package ru.multicon.pgslowpoke.domain;

import lombok.*;
import lombok.experimental.Wither;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Wither
public class PgSettings {
    private String name;
    private String value; // текущее значение. Расчитывается как setting * unit
    private String setting;
    private String min;
    private String max;
    private String unit;
    private String description;

    public PgSettings(String name, String value, String setting, String min, String max, String unit) {
        this.name = name;
        this.value = value;
        this.setting = setting;
        this.min = min;
        this.max = max;
        this.unit = unit;
    }
}
