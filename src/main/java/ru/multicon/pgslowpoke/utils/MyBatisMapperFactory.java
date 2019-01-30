package ru.multicon.pgslowpoke.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBatisMapperFactory {

    public <T> T create(HikariDataSource dataSource , Class type) {
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





}
