package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PgStatUserTables {
    private String schema;  // Схема
    private String table;   // Таблица
    private long seqScan;     // Последовательный просмотр
    private long seqTupRead;  // Кол-во записей(Последовательный просмотр)
    private long indexScan;     // Просмотр по индексу
    private long indexTupFetch; // Кол-во записей(Просмотр по индексу)
    private long insert; // Количество вставленных строк
    private long update; // Количество изменённых строк (включая изменения по схеме HOT)
    private long delete; // Количество удалённых строк
}
