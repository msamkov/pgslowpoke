CREATE TABLE s_test.t_index_unused
(
  id serial,
  name text,
  title text,
  enabled boolean DEFAULT FALSE NOT NULL,
  size int,
  CONSTRAINT "t_index_unused_pkey" PRIMARY KEY (id)
);

COMMENT ON TABLE s_test.t_index_unused IS 'Таблица для тестирования поиска неиспользуемых индексов';
COMMENT ON COLUMN s_test.t_index_unused.id IS 'Идентификатор';
COMMENT ON COLUMN s_test.t_index_unused.name IS 'Наименование';
COMMENT ON COLUMN s_test.t_index_unused.title IS 'Описание';
COMMENT ON COLUMN s_test.t_index_unused.enabled IS 'Активный/неактивный';
COMMENT ON COLUMN s_test.t_index_unused.size IS 'Размер';

CREATE INDEX t_index_unused_name_idx
  ON s_test.t_index_unused
  USING btree(name);

CREATE INDEX t_index_unused_title_idx
  ON s_test.t_index_unused
  USING btree(title);

CREATE INDEX t_index_unused_size_idx
  ON s_test.t_index_unused
  USING btree(size);