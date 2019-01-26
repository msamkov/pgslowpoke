package ru.multicon.pgslowpoke.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class PgSettingsHint {
    private String name;
    private String description;
    private String hint;
}
