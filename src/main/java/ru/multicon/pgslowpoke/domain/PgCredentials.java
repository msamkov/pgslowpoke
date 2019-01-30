package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PgCredentials {
    private String host;
    private String database;
    private String port;
    private String user;
    private String password;
}
