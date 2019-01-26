package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.multicon.pgslowpoke.domain.IndexSize;

import java.util.List;

public interface IndexSizeRepository {

    @Select("SELECT schemaname AS schema,\n" +
            "       tablename AS table,\n" +
            "       indexname AS index,\n" +
            "       pg_relation_size(format('%I.%I', schemaname, indexname)) AS size\n" +
            "FROM pg_indexes\n" +
            "WHERE schemaname NOT IN ('pg_catalog','information_schema');\n")
    @Results(id = "IndexSizeResult",
            value = {@Result(property = "schema", column = "schema"),
                     @Result(property = "table", column = "table"),
                     @Result(property = "index", column = "index"),
                     @Result(property = "size", column = "size")
            })
    List<IndexSize> findAll();
}
