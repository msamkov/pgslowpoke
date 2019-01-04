package ru.multicon.pgslowpoke.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.multicon.pgslowpoke.domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RepositoryIntegrationTest {

    private final static String CHANGE_LOG_FILE_PATH = "src/main/resources/db/db.changelog-master.xml";

    private static ForeignKeyDuplicateRepository foreignKeyDuplicateRepository;
    private static IndexDuplicateRepository indexDuplicateRepository;
    private static IndexUnusedRepository indexUnusedRepository;
    private static PgSettingsRepository pgSettingsRepository;
    private static PgStatUserTablesRepository pgStatUserTablesRepository;

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer();

    @BeforeClass
    public static void setUp() throws Exception {
        HikariDataSource dataSource = getDataSource();
        liqubaseUpdate(dataSource);
        initMapper(dataSource);
    }

    private static void initMapper(HikariDataSource dataSource) {
        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();
        Environment environment =
                new Environment("test", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(ForeignKeyDuplicateRepository.class);
        configuration.addMapper(IndexDuplicateRepository.class);
        configuration.addMapper(IndexUnusedRepository.class);
        configuration.addMapper(PgSettingsRepository.class);
        configuration.addMapper(PgStatUserTablesRepository.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        foreignKeyDuplicateRepository = session.getMapper(ForeignKeyDuplicateRepository.class);
        indexDuplicateRepository = session.getMapper(IndexDuplicateRepository.class);
        indexUnusedRepository = session.getMapper(IndexUnusedRepository.class);
        pgSettingsRepository = session.getMapper(PgSettingsRepository.class);
        pgStatUserTablesRepository = session.getMapper(PgStatUserTablesRepository.class);
    }

    private static HikariDataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(postgres.getJdbcUrl());
        hikariConfig.setUsername(postgres.getUsername());
        hikariConfig.setPassword(postgres.getPassword());
        return new HikariDataSource(hikariConfig);
    }

    private static void liqubaseUpdate(HikariDataSource dataSource) throws LiquibaseException, SQLException {
        Connection c = dataSource.getConnection();
        Database database = DatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(c));
        Liquibase liquibase = new Liquibase(CHANGE_LOG_FILE_PATH, new FileSystemResourceAccessor(), database);
        liquibase.update("");
    }


    @Test
    public void foreignKeyDuplicateFindAll() {
        //подготавливаем
        int expected = 2;

        //выполняем
        List<ForeignKeyDuplicate> foreignKeyDuplicates = foreignKeyDuplicateRepository.findAll();
        int actual = foreignKeyDuplicates.size();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void indexDuplicateRepositoryFindAll() {
        //подготавливаем
        int expected = 2;

        //выполняем
        List<IndexDuplicate> indexDuplicates = indexDuplicateRepository.findAll();
        int actual = indexDuplicates.size();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void indexUnusedRepositoryFindAll() {
        //подготавливаем
        int expected = 2;

        //выполняем
        List<IndexUnused> indexUnuseds = indexUnusedRepository.findAll();
        int actual = indexUnuseds.size();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void pgSettingsRepositoryFindAll() {
        //подготавливаем
        int min = 50;

        //выполняем
        List<PgSettings> pgSettings = pgSettingsRepository.findAll();
        int actual = pgSettings.size();

        //сравниваем
        Assert.assertTrue(actual > min);
    }

    @Test
    public void pgSettingsRepositoryFindPrimarySettings() {
        //подготавливаем
        int expected = 9;

        //выполняем
        List<PgSettings> pgSettings = pgSettingsRepository.findPrimarySettings();
        int actual = pgSettings.size();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void pgStatUserTablesFindAll() {
        //подготавливаем
        int expected = 6;

        //выполняем
        List<PgStatUserTables> pgStatUserTables = pgStatUserTablesRepository.findAll();
        int actual = pgStatUserTables.size();

        //сравниваем
        Assert.assertEquals(expected, actual);
    }

}