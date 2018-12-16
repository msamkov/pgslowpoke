package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.multicon.pgslowpoke.domain.ForeignKeyDuplicate;
import java.util.List;

@Mapper
public interface RepositoryForeignKeyDuplicate {

    @Select("WITH duplicate_foreign_key_group AS\n" +
            "    (\n" +
            "    SELECT\n" +
            "           array_agg(pc.conname) AS constraint_name,\n" +
            "           pclsc.relname as child_table,\n" +
            "           pac.attname as child_column,\n" +
            "           pclsp.relname as parent_table,\n" +
            "           pap.attname as parent_column\n" +
            "    FROM\n" +
            "         (\n" +
            "         SELECT\n" +
            "                connamespace,conname, unnest(conkey) as \"conkey\", unnest(confkey)\n" +
            "                                                     as \"confkey\" , conrelid, confrelid, contype\n" +
            "         FROM\n" +
            "              pg_constraint\n" +
            "         ) pc\n" +
            "           JOIN pg_namespace pn ON pc.connamespace = pn.oid\n" +
            "           JOIN pg_class pclsc ON pc.conrelid = pclsc.oid\n" +
            "           JOIN pg_class pclsp ON  pc.confrelid = pclsp.oid\n" +
            "           JOIN pg_attribute pac ON pc.conkey = pac.attnum    and pac.attrelid =  pclsc.oid\n" +
            "           JOIN pg_attribute pap ON pc.confkey = pap.attnum and pap.attrelid = pclsp.oid\n" +
            "    GROUP BY 2,3,4,5\n" +
            "    HAVING COUNT(*) > 1\n" +
            "    ), duplicate_foreign_key AS\n" +
            "    (\n" +
            "    SELECT\n" +
            "\n" +
            "           UNNEST(constraint_name) AS constraint_name,\n" +
            "           child_table,\n" +
            "           child_column,\n" +
            "           parent_table,\n" +
            "           parent_column\n" +
            "    FROM duplicate_foreign_key_group\n" +
            "    )\n" +
            "SELECT child_table,\n" +
            "       child_column,\n" +
            "       parent_table,\n" +
            "       parent_column,\n" +
            "       constraint_name\n" +
            "FROM duplicate_foreign_key;")
    @Results(id = "ForeignKeyDuplicateResult",
            value = {@Result(property = "childTable", column = "child_table"),
                     @Result(property = "childColumn", column = "child_column"),
                     @Result(property = "parentTable", column = "parent_table"),
                     @Result(property = "parentColumn", column = "parent_column"),
                     @Result(property = "constraintName", column = "constraint_name")
            })
    List<ForeignKeyDuplicate> findAll();
}
