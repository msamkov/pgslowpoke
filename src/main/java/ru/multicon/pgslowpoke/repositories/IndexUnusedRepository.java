package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.multicon.pgslowpoke.domain.IndexUnused;

import java.util.List;

@Mapper
public interface IndexUnusedRepository {

    @Select("WITH tbl_pk_index AS\n" +
            "    (\n" +
            "    SELECT n.nspname AS \"schema\",\n" +
            "           t.relname AS \"table\",\n" +
            "           c.relname AS \"index\",\n" +
            "           i.indexrelid AS index_id\n" +
            "    FROM pg_catalog.pg_class c\n" +
            "    JOIN pg_catalog.pg_namespace n\n" +
            "      ON n.oid = c.relnamespace\n" +
            "    JOIN pg_catalog.pg_index i\n" +
            "      ON i.indexrelid = c.oid\n" +
            "    JOIN pg_catalog.pg_class t\n" +
            "      ON i.indrelid = t.oid\n" +
            "    WHERE c.relkind = 'i'\n" +
            "      AND n.nspname NOT IN ('pg_catalog','pg_toast')\n" +
            "      AND indisprimary -- первичный ключ\n" +
            "    ),\n" +
            "    tbl_drop_index AS\n" +
            "    (\n" +
            "      SELECT schemaname, relname \n" +
            "      FROM  pg_stat_user_tables\n" +
            "      WHERE seq_scan > 100  -- более 100 полных проходов по таблице\n" +
            "      AND ( n_tup_ins + n_live_tup ) > 1   -- таблица не пуста\n" +
            "    )\n" +
            "    SELECT i.schemaname AS schema,\n" +
            "           i.relname AS table,\n" +
            "           i.indexrelname AS index,\n" +
            "           pg_relation_size(i.indexrelid::regclass) AS size \n" +
            "    FROM pg_stat_user_indexes AS i\n" +
            "    JOIN pg_index AS ind\n" +
            "      ON i.indexrelid = ind.indexrelid\n" +
            "    JOIN tbl_drop_index AS di\n" +
            "      ON i.schemaname = di.schemaname   \n" +
            "        AND i.relname = di.relname      \n" +
            "    LEFT JOIN tbl_pk_index AS pk\n" +
            "      ON i.indexrelname = pk.index\n" +
            "    WHERE i.idx_scan = 0 --индекс не используется\n" +
            "    AND pk.index IS NULL\n" +
            "    AND ind.indisunique IS FALSE; -- индекс не уникальный")
    @Results(id = "IndexUnusedResult",
            value = {@Result(property = "schema", column = "schema"),
                     @Result(property = "table", column = "table"),
                     @Result(property = "index", column = "index"),
                     @Result(property = "size", column = "size")
            })
    List<IndexUnused> findAll();
}
