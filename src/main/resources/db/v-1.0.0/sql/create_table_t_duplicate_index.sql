CREATE SCHEMA s_test;

CREATE TABLE s_test.t_duplicate_index
(
  id int,
  name text,
  title text,
  enabled boolean DEFAULT FALSE NOT NULL,
  CONSTRAINT "t_duplicate_index_pkey" PRIMARY KEY (id)
);

COMMENT ON TABLE s_test.t_duplicate_index IS 'Таблица для тестирования поиска повторяющихся индексов';
COMMENT ON COLUMN s_test.t_duplicate_index.id IS 'Идентификатор';
COMMENT ON COLUMN s_test.t_duplicate_index.name IS 'Наименование';
COMMENT ON COLUMN s_test.t_duplicate_index.title IS 'Описание';
COMMENT ON COLUMN s_test.t_duplicate_index.enabled IS 'Активный/неактивный';


CREATE INDEX t_duplicate_index_name_idx
  ON s_test.t_duplicate_index
  USING btree(name);

CREATE INDEX t_duplicate_index_name2_idx
  ON s_test.t_duplicate_index
  USING btree(name);