CREATE TABLE s_test.t_user
(
  id int,
  name text,
  CONSTRAINT "t_user_pkey" PRIMARY KEY (id)
);
COMMENT ON TABLE  s_test.t_user IS 'Пользователь';
COMMENT ON COLUMN s_test.t_user.id IS 'Идентификатор';
COMMENT ON COLUMN s_test.t_user.name IS 'Наименование';

CREATE TABLE s_test.t_foreign_key_duplicate
(
  id int,
  name text,
  title text,
  user_id int,
  CONSTRAINT "t_foreign_key_duplicate_pkey" PRIMARY KEY (id)
);

COMMENT ON TABLE  s_test.t_foreign_key_duplicate IS 'Таблица для тестирования поиска внешних ключей';
COMMENT ON COLUMN s_test.t_foreign_key_duplicate.id IS 'Идентификатор';
COMMENT ON COLUMN s_test.t_foreign_key_duplicate.name IS 'Наименование';
COMMENT ON COLUMN s_test.t_foreign_key_duplicate.title IS 'Описание';
COMMENT ON COLUMN s_test.t_foreign_key_duplicate.user_id IS 'Идентификатор пользователя ';


ALTER TABLE s_test.t_foreign_key_duplicate
ADD CONSTRAINT t_foreign_key_duplicate_t_user_fkey FOREIGN KEY (user_id)
  REFERENCES s_test.t_user (id)
  MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE s_test.t_foreign_key_duplicate
  ADD CONSTRAINT t_foreign_key_duplicate_t_user_fkey2 FOREIGN KEY (user_id)
  REFERENCES s_test.t_user (id)
  MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
