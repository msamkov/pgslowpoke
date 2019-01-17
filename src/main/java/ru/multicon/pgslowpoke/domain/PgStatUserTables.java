package ru.multicon.pgslowpoke.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PgStatUserTables {
    private String schema;  // Схема
    private String table;   // Таблица
    private Long seqScan;     // Последовательный просмотр
    private Long seqTupRead;  // Кол-во записей(Последовательный просмотр)
    private Long indexScan;     // Просмотр по индексу
    private Long indexTupFetch; // Кол-во записей(Просмотр по индексу)
    private Long insert; // Количество вставленных строк
    private Long update; // Количество изменённых строк (включая изменения по схеме HOT)
    private Long delete; // Количество удалённых строк
}
