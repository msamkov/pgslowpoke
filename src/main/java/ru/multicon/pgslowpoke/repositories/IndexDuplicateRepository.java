package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.*;
import ru.multicon.pgslowpoke.domain.IndexDuplicate;

import java.util.List;

@Mapper
public interface IndexDuplicateRepository {

    @Select("WITH duplicate_index AS\n" +
            "    (\n" +
            "    SELECT indrelid::regclass::text AS tablename,\n" +
            "           array_agg(indexrelid::regclass::text) AS indexes,\n" +
            "           COUNT(*)\n" +
            "    FROM pg_index\n" +
            "    GROUP BY\n" +
            "             indrelid,\n" +
            "             indkey\n" +
            "    HAVING COUNT(*) > 1\n" +
            "    )\n" +
            "  SELECT ind.tablename AS table,\n" +
            "         ind.indexname AS index,\n" +
            "         ind.indexdef AS sql,\n" +
            "       CASE WHEN i.indisprimary THEN true\n" +
            "            ELSE false\n" +
            "           END AS is_primary_key\n" +
            "FROM pg_indexes AS ind\n" +
            "JOIN pg_index AS i\n" +
            "  ON i.indexrelid::regclass::text = ind.indexname\n" +
            "  OR i.indexrelid::regclass::text = format('%s.%s', ind.schemaname, ind.indexname)\n" +
            "JOIN duplicate_index AS di\n" +
            "  ON ( di.tablename = ind.tablename\n" +
            "        OR\n" +
            "       di.tablename = format('%s.%s', ind.schemaname, ind.tablename)\n" +
            "      )\n" +
            "  AND (\n" +
            "         ind.indexname::text = ANY(di.indexes)\n" +
            "         OR\n" +
            "         format('%s.%s', ind.schemaname, ind.indexname) = ANY(di.indexes)\n" +
            "      )\n" +
            "\n")
    @Results(id = "IndexDuplicateResult",
            value = {@Result(property = "table", column = "table"),
                    @Result(property = "index", column = "index"),
                    @Result(property = "sql", column = "sql"),
                    @Result(property = "isPrimaryKey", column = "is_primary_key")
            })
    List<IndexDuplicate> findAll();
}
