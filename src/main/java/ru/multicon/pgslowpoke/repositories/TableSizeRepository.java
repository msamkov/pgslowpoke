package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.multicon.pgslowpoke.domain.TableSize;

import java.util.List;

@Mapper
public interface TableSizeRepository {

    @Select("SELECT schemaname AS schema, \n" +
            "       tablename AS table, \n" +
            "       pg_table_size(format('%s.%s', schemaname, tablename)) AS size \n" +
            "FROM pg_tables \n" +
            "WHERE schemaname NOT IN ('pg_catalog','information_schema');")
    @Results(id = "TableSizeResult",
            value = {@Result(property = "schema", column = "schema"),
                     @Result(property = "table", column = "table"),
                     @Result(property = "size", column = "size")
            })
    List<TableSize> findAll();
}
