package ru.multicon.pgslowpoke.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Component;
import ru.multicon.pgslowpoke.domain.PgCredentials;

@Component
public class MyBatisMapperFactory {

    public <T> T create(PgCredentials pgCredentials, Class type) {
        HikariDataSource dataSource = getDataSource(pgCredentials);
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment(type.getTypeName(), transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(type);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        return (T) session.getMapper(type);

    }

    private HikariDataSource getDataSource(PgCredentials pgCredentials) {
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
