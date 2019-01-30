package ru.multicon.pgslowpoke.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.domain.PgCredentials;

@Component
public class DataSourceFactory {
    public HikariDataSource create(PgCredentials pgCredentials) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(
                String.format("jdbc:postgresql://%s:%s/%s",
                        pgCredentials.getHost(),
                        pgCredentials.getPort(),
                        pgCredentials.getDatabase())
        );
        hikariConfig.setUsername(pgCredentials.getUser());
        hikariConfig.setPassword(pgCredentials.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
