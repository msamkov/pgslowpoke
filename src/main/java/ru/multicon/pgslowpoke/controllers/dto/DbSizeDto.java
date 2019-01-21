package ru.multicon.pgslowpoke.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DbSizeDto {
    private String name;
    private String size;
}
