package ru.multicon.pgslowpoke.services;

import org.springframework.stereotype.Service;
import ru.multicon.pgslowpoke.domain.PgSettingsHint;
import java.util.Arrays;
import java.util.List;

@Service
public class PgSettingsHintService {
    private final List<PgSettingsHint> pgSettingsHints =
            Arrays.asList(
                    new PgSettingsHint()
                        .setName("max_connections")
                        .setDescription("Максимальное число одновременных подключений к серверу БД"),
                    new PgSettingsHint()
                        .setName("shared_buffers")
                        .setDescription("Объём совместно используемой памяти, выделяемой PostgreSQL" +
                                " для кэширования данных, определяется числом страниц (shared_buffers)" +
                                " по 8 килобайт каждая. Следует учитывать, что операционная система " +
                                "сама кеширует данные, поэтому нет необходимости отводить под кэш" +
                                " всю оперативную память")
            );

    public List<PgSettingsHint> findAll() {
        return pgSettingsHints;
    }
}
