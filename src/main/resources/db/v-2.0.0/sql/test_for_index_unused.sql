-- Создаём 4000 тестовых строк
INSERT INTO s_test.t_index_unused(id, name, title, enabled, size)
SELECT g AS id,
       md5(random()::text) as name,
       md5(random()::text) as title,
       random()::int::boolean AS enabled,
       random()*1000 as size
FROM generate_series(1,1000) AS g;
ANALYZE s_test.t_index_unused;

INSERT INTO s_test.t_index_unused(id, name, title, enabled, size)
SELECT g AS id,
       md5(random()::text) as name,
       md5(random()::text) as title,
       random()::int::boolean AS enabled,
       random()*1000 as size
FROM generate_series(1001,2000) AS g;
ANALYZE s_test.t_index_unused;

INSERT INTO s_test.t_index_unused(id, name, title, enabled, size)
SELECT g AS id,
       md5(random()::text) as name,
       md5(random()::text) as title,
       random()::int::boolean AS enabled,
       random()*1000 as size
FROM generate_series(2001,3000) AS g;
ANALYZE s_test.t_index_unused;

INSERT INTO s_test.t_index_unused(id, name, title, enabled, size)
SELECT g AS id,
       md5(random()::text) as name,
       md5(random()::text) as title,
       random()::int::boolean AS enabled,
       random()*1000 as size
FROM generate_series(3001,4000) AS g;
ANALYZE s_test.t_index_unused;

-- Делаем 300 полных проходов по таблице
SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name ~ ('^ZZZ' || g::text)   )
FROM generate_series(1,300) AS g;
ANALYZE s_test.t_index_unused;

-- Используем индекс для поля name
SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name = g::text )
FROM generate_series(1,50) AS g;
ANALYZE s_test.t_index_unused;

SELECT
       ( SELECT name FROM s_test.t_index_unused WHERE name = g::text )
FROM generate_series(1,3) AS g;
ANALYZE s_test.t_index_unused;

