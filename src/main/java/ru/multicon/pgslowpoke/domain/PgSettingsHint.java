package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class PgSettingsHint {
    private String name;
    private String description;
    private String hint;
}
