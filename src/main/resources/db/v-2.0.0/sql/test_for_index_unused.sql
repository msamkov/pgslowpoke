-- Создаём 40000 тестовых строк
INSERT INTO s_test.t_index_unused(id, name, title, enabled, size)
SELECT g AS id,
       md5(random()::text) as name,
       md5(random()::text) as title,
       random()::int::boolean AS enabled,
       random()*1000 as size
FROM generate_series(1,40000) AS g;


-- Делаем 300 полных проходов по таблице
SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name ~ ('^ZZZ' || g::text)   )
FROM generate_series(1,300) AS g;

-- Используем индекс для поля name
SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name = g::text )
FROM generate_series(1,50) AS g;

SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name = g::text )
FROM generate_series(1,3) AS g;

-- Собрать статистику по БД
ANALYSE;