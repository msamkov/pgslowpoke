package ru.multicon.pgslowpoke.repositories;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.multicon.pgslowpoke.domain.PgStatUserTables;

import java.util.List;


public interface PgStatUserTablesRepository {

    @Select("SELECT schemaname, \n" +
            "       relname, \n" +
            "       seq_scan, \n" +
            "       seq_tup_read, \n" +
            "       idx_scan, \n" +
            "       idx_tup_fetch, \n" +
            "       n_tup_ins, \n" +
            "       n_tup_upd, \n" +
            "       n_tup_del \n" +
            "FROM  pg_stat_user_tables;")
    @Results(id = "PgStatUserTablesResult",
            value = {@Result(property = "schema", column = "schemaname"),
                    @Result(property = "table", column = "relname"),
                    @Result(property = "seqScan", column = "seq_scan"),
                    @Result(property = "seqTupRead", column = "seq_tup_read"),
                    @Result(property = "indexScan", column = "idx_scan"),
                    @Result(property = "indexTupFetch", column = "idx_tup_fetch"),
                    @Result(property = "insert", column = "n_tup_ins"),
                    @Result(property = "update", column = "n_tup_upd"),
                    @Result(property = "delete", column = "n_tup_del")
            })
    List<PgStatUserTables> findAll();

}
