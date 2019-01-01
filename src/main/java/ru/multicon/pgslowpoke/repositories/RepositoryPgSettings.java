package ru.multicon.pgslowpoke.repositories;

import org.apache.ibatis.annotations.*;
import ru.multicon.pgslowpoke.domain.PgSettings;

import java.util.List;

@Mapper
public interface RepositoryPgSettings {

    @Select("SELECT name, \n" +
            "       current_setting(name) as value, \n" +
            "       setting, \n" +
            "       min_val, \n" +
            "       max_val, \n" +
            "       unit \n" +
            "FROM pg_settings; ")
    @Results(id = "PgSettingsResult",
            value = {@Result(property = "name", column = "name"),
                     @Result(property = "value", column = "value"),
                     @Result(property = "setting", column = "setting"),
                     @Result(property = "min", column = "min_val"),
                     @Result(property = "max", column = "max_val"),
                     @Result(property = "unit", column = "unit")
            })
    List<PgSettings> findAll();


    @Select("SELECT name, \n" +
            "       current_setting(name) as value, \n" +
            "       setting, " +
            "       min_val, " +
            "       max_val, " +
            "       unit \n" +
            "FROM pg_settings \n" +
            "WHERE name IN ('max_connections',\n" +
            "    'shared_buffers',\n" +
            "    'effective_cache_size',\n" +
            "    'maintenance_work_mem',\n" +
            "    'wal_buffers',\n" +
            "    'work_mem',\n" +
            "    'checkpoint_segments',\n" +
            "    'checkpoint_completion_target',\n" +
            "    'effective_io_concurrency',\n" +
            "    'default_statistics_target');")
    @ResultMap("PgSettingsResult")
    List<PgSettings> findPrimarySettings();





}
