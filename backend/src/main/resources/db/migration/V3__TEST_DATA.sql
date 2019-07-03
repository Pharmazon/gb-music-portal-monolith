--группы исполнителей
INSERT INTO app_music_group (id, creation_date, description, last_update, name)
    VALUES (1, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Лучшая группа', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'METALLICA');

INSERT INTO app_music_group (id, creation_date, description, last_update, name)
    VALUES (2, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Один исполнитель', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'Иыван Иванов');

INSERT INTO app_music_group (id, creation_date, description, last_update, name)
    VALUES (3, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Дуэт', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'Иван Иванов и Марина');

--типы свойств треков
INSERT INTO app_music_feature_type (id, creation_date, description, last_update, name)
VALUES (1, to_timestamp('2019-06-11', 'yyyy-mm-dd'), 'INT', to_timestamp('2019-06-11', 'yyyy-mm-dd'), 'Битрейт, кбит/с');

INSERT INTO app_music_feature_type (id, creation_date, description, last_update, name)
VALUES (2, to_timestamp('2019-06-15', 'yyyy-mm-dd'), 'STRING', to_timestamp('2019-06-15', 'yyyy-mm-dd'), 'Свойство A');

INSERT INTO app_music_feature_type (id, creation_date, description, last_update, name)
VALUES (3, to_timestamp('2019-05-16', 'yyyy-mm-dd'), 'BOOLEAN', to_timestamp('2019-06-11', 'yyyy-mm-dd'), 'Свойство B');

INSERT INTO app_music_feature_type (id, creation_date, description, last_update, name)
VALUES (4, to_timestamp('2019-04-12', 'yyyy-mm-dd'), 'STRING', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Свойство C');

--жанры
INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (1, to_timestamp('2019-04-15', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Эстрада', null);

INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (2, to_timestamp('2019-04-15', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Джаз', null);

INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (3, to_timestamp('2019-04-15', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Блюз', null);

INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (4, to_timestamp('2019-04-15', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Классика', null);

INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (5, to_timestamp('2019-04-15', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'Опера', 4);

INSERT INTO app_categories (id, creation_date, description, last_update, name, parent_id)
  VALUES (6, to_timestamp('2019-06-05', 'yyyy-mm-dd'), '', to_timestamp('2019-06-01', 'yyyy-mm-dd'), 'METALL', null);


--треки
INSERT INTO app_track (id, creation_date, description, last_update, name, link, music_group_id)
VALUES (1, current_timestamp, 'Песня о жизни', current_timestamp, 'О жизни', 'c:\tracks\1', 2);

INSERT INTO app_track (id, creation_date, description, last_update, name, link, music_group_id)
VALUES (2, current_timestamp, 'Юмор', current_timestamp, 'На лабутенах', 'c:\tracks\2', 3);

INSERT INTO app_track (id, creation_date, description, last_update, name, link, music_group_id)
VALUES (3, current_timestamp, 'Поем вечные хиты', current_timestamp, 'Unforgiven', 'c:\tracks\3', 3);

--трек-категория
INSERT INTO join_track_category (track_id, category_id)
    VALUES(1, 1);
INSERT INTO join_track_category (track_id, category_id)
    VALUES(1, 6);
INSERT INTO join_track_category (track_id, category_id)
    VALUES(2, 2);
INSERT INTO join_track_category (track_id, category_id)
    VALUES(3, 6);

--значения свойств
INSERT INTO app_music_feature (id, creation_date, description, last_update, name, music_feature_type_id, track_id)
  VALUES (1, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'36', to_timestamp('2019-01-30', 'yyyy-mm-dd'), '36', 1, 1);
INSERT INTO app_music_feature (id, creation_date, description, last_update, name, music_feature_type_id, track_id)
  VALUES (2, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'слово', to_timestamp('2019-01-30', 'yyyy-mm-dd'), 'слово', 2, 1);
INSERT INTO app_music_feature (id, creation_date, description, last_update, name, music_feature_type_id, track_id)
  VALUES (3, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'TRUE', to_timestamp('2019-01-30', 'yyyy-mm-dd'), 'TRUE', 3, 1);

--роли
INSERT INTO app_roles (id, creation_date, description, last_update, name) VALUES (1, current_timestamp, 'Может все', current_timestamp, 'ADMIN');
INSERT INTO app_roles (id, creation_date, description, last_update, name) VALUES (2, current_timestamp, 'Пользователь портала', current_timestamp, 'USER');
INSERT INTO app_roles (id, creation_date, description, last_update, name) VALUES (3, current_timestamp, 'Исполнитель', current_timestamp, 'ARTIST');


-- пользователи
INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, last_login_date, last_password_change_date, username, music_group_id)
VALUES(1, to_timestamp('2019-01-30', 'yyyy-mm-dd'), current_timestamp, 'Билл Гейтс', '123456', 'все гуд', 'Как дела?', 3, FALSE, to_timestamp('2019-03-30', 'yyyy-mm-dd'), to_timestamp('2019-02-18', 'yyyy-mm-dd'), 'billy', NULL);

INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, last_login_date, last_password_change_date, username, music_group_id)
VALUES(2, to_timestamp('2019-05-11', 'yyyy-mm-dd'), current_timestamp, 'Иван Иванов', '123123', 'Я Бэтман', 'Кто ты?', 3, FALSE, to_timestamp('2019-05-30', 'yyyy-mm-dd'), to_timestamp('2019-06-15', 'yyyy-mm-dd'), 'Ivan', 3);

INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, last_login_date, last_password_change_date, username, music_group_id)
VALUES(3, to_timestamp('2019-06-11', 'yyyy-mm-dd'), current_timestamp, 'Марина', '654321', 'Отличная', 'Как погодка?', 3, TRUE , to_timestamp('2019-06-12', 'yyyy-mm-dd'), to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'marina', 3);

INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, last_login_date, last_password_change_date, username, music_group_id)
VALUES(4, to_timestamp('2019-06-11', 'yyyy-mm-dd'), current_timestamp, 'Вероника', '121212', 'Иванова', 'Девчья фамилия матери?', 3, FALSE , to_timestamp('2019-06-12', 'yyyy-mm-dd'), to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'nika', null);

--пользователь-роль
INSERT INTO join_user_roles (user_id, role_id)
    VALUES(1, 1);
INSERT INTO join_user_roles (user_id, role_id)
    VALUES(2, 3);
INSERT INTO join_user_roles (user_id, role_id)
    VALUES(3, 3);

--пользователь-профиль
INSERT INTO app_user_profiles (id, creation_date, last_update, birth_date, city, country, email, first_name, hobby, last_name, mobile_phone, site, skype, user_id)
  VALUES (1, current_timestamp, current_timestamp, to_timestamp('1990-01-05', 'yyyy-mm-dd'), 'Москва', 'Россия', '111@222.ru', 'Билл', 'Прыжки с парашютом', 'Гейтс', '8-909-111-11-11', NULL, NULL, 1 );

--плейлисты
INSERT INTO app_playlist (id, creation_date, description, last_update, name, user_id) VALUES (
    1, current_timestamp, 'Любимый плейлист Вероники', current_timestamp, 'хиты', 4);
INSERT INTO app_playlist (id, creation_date, description, last_update, name, user_id) VALUES (
    2, current_timestamp, 'Билл слушает на работе', current_timestamp, 'работа', 1);
--плейлист-трек
INSERT INTO join_playlist_track (track_id, playlist_id) VALUES(1,1);
INSERT INTO join_playlist_track (track_id, playlist_id) VALUES(2,1);
INSERT INTO join_playlist_track (track_id, playlist_id) VALUES(2,2);
INSERT INTO join_playlist_track (track_id, playlist_id) VALUES(3,2);
INSERT INTO join_playlist_track (track_id, playlist_id) VALUES(3,1);

--типы свойств плейлиста
INSERT INTO app_playlist_feature_type (id, creation_date, description, last_update, name)
  VALUES(1, current_timestamp, 'Строка', current_timestamp, 'Свойство А');
INSERT INTO app_playlist_feature_type (id, creation_date, description, last_update, name)
VALUES(2, current_timestamp, 'BOOLEAN', current_timestamp, 'Свойство Б') ;


--свойства плейлиста
INSERT INTO app_playlist_feature (id, creation_date, description, last_update, name, playlist_id, playlist_feature_type_id)
  VALUES (1, current_timestamp, 'слово', current_timestamp, 'слово', 1, 1);
INSERT INTO app_playlist_feature (id, creation_date, description, last_update, name, playlist_id, playlist_feature_type_id)
  VALUES (2, current_timestamp, 'TRUE', current_timestamp, 'TRUE', 1, 2);

--Статьи блога
INSERT INTO app_articles (id, creation_date, description, last_update, name, content, short_description, title, author_id)
      VALUES (1, current_timestamp, 'Описание', current_timestamp, NULL , 'слово слово слово слово слово слово слово слово слово слово слово слово слово', 'Краткое описание темы', 'Название 1', 2);
INSERT INTO app_articles (id, creation_date, description, last_update, name, content, short_description, title, author_id)
  VALUES (2, current_timestamp, 'Описание описание', current_timestamp, NULL , 'Предложение предложение предложение предложение', NULL , 'Название 2', 2);

--Комментарии к статье
INSERT INTO app_comments (id, creation_date, description, last_update, name, comment_content, article_id)
  VALUES (1, current_timestamp, '', current_timestamp, 'Название комментария 1', 'слово слово слово слово слово слово слово слово слово ', 1);
INSERT INTO app_comments (id, creation_date, description, last_update, name, comment_content, article_id)
  VALUES (2, current_timestamp, '', current_timestamp, 'Название комментария 2', 'Предложение предложение', 1);
