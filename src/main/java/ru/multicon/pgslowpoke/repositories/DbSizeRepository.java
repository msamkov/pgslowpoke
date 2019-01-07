package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.*;
import ru.multicon.pgslowpoke.domain.DbSize;

@Mapper
public interface DbSizeRepository {

    @Select("SELECT #{name} AS name,  \n" +
            "pg_size_pretty( pg_database_size(#{name})) AS size;")
    @Results(id = "DbSizeResult",
            value = {@Result(property = "name", column = "name"),
                     @Result(property = "size", column = "size")
            })
    DbSize findByName(String name);

    @Select("SELECT current_database() AS name,\n" +
            "pg_size_pretty( pg_database_size(current_database())) AS size;")
    @ResultMap("DbSizeResult")
    DbSize current();

}
