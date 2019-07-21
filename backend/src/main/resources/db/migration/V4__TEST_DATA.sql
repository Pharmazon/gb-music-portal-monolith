--исполнители
INSERT INTO app_artists (id, creation_date, description, last_update, name)
    VALUES (1, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Лучшая группа', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'METALLICA');

INSERT INTO app_artists (id, creation_date, description, last_update, name)
    VALUES (2, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Один исполнитель', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'Иыван Иванов');

INSERT INTO app_artists (id, creation_date, description, last_update, name)
    VALUES (3, to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'Дуэт', to_timestamp('2019-06-05', 'yyyy-mm-dd'), 'Иван Иванов и Марина');

--типы свойств треков
INSERT INTO app_track_feature_types (id, description, name) VALUES (4, 'INT', 'Битрейт, кбит/с');
INSERT INTO app_track_feature_types (id, description, name) VALUES (5, 'STRING', 'Свойство A');
INSERT INTO app_track_feature_types (id, description, name) VALUES (6, 'BOOLEAN', 'Свойство B');
INSERT INTO app_track_feature_types (id, description, name) VALUES (7, 'STRING', 'Свойство C');

--картинки
INSERT INTO app_images (id, creation_date, description, last_update, name, img_link)
VALUES (1, current_timestamp, null, current_timestamp, null, '/opt/photos/user01/1');
INSERT INTO app_images (id, creation_date, description, last_update, name, img_link)
VALUES (2, current_timestamp, null, current_timestamp, null, '/opt/photos/user02/1');
INSERT INTO app_images (id, creation_date, description, last_update, name, img_link)
VALUES (3, current_timestamp, null, current_timestamp, null, '/opt/photos/user03/1');
INSERT INTO app_images (id, creation_date, description, last_update, name, img_link)
VALUES (4, current_timestamp, null, current_timestamp, null, '/opt/photos/user04/1');
INSERT INTO app_images (id, creation_date, description, last_update, name, img_link)
VALUES (5, current_timestamp, null, current_timestamp, null, '/opt/photos/user05/1');

--жанры
INSERT INTO app_genres (id, name) VALUES (8, 'Эстрадная');
INSERT INTO app_genres (id, name) VALUES (9, 'Джаз');
INSERT INTO app_genres (id, name) VALUES (10, 'Блюз');
INSERT INTO app_genres (id, name) VALUES (11, 'Классика');
INSERT INTO app_genres (id, name) VALUES (12, 'Опера');
INSERT INTO app_genres (id, name) VALUES (13, 'Танцевальная');
INSERT INTO app_genres (id, name) VALUES (14, 'Поп');
INSERT INTO app_genres (id, name) VALUES (15, 'Хип-хоп');
INSERT INTO app_genres (id, name) VALUES (16, 'Рэп');
INSERT INTO app_genres (id, name) VALUES (17, 'Металл');
INSERT INTO app_genres (id, name) VALUES (18, 'Рок');

--треки
INSERT INTO app_tracks (id, creation_date, description, last_update, name, link, artist_id)
    VALUES (14, current_timestamp, 'Песня о жизни', current_timestamp, 'О жизни', '14.mp3', 2);
INSERT INTO app_tracks (id, creation_date, description, last_update, name, link, artist_id)
    VALUES (15, current_timestamp, 'Юмор', current_timestamp, 'На лабутенах', '15.mp3', 3);
INSERT INTO app_tracks (id, creation_date, description, last_update, name, link, artist_id)
    VALUES (16, current_timestamp, 'Поем вечные хиты', current_timestamp, 'Unforgiven', '16.mp3', 3);
INSERT INTO app_tracks (id, creation_date, description, last_update, name, link, artist_id)
    VALUES (17, current_timestamp, '', current_timestamp, 'Zebra crossing the street', '17.mp3', 3);
INSERT INTO app_tracks (id, creation_date, description, last_update, name, link, artist_id)
    VALUES (18, current_timestamp, '', current_timestamp, 'Coldwater Canyon', '18.mp3', 2);

--трек-жанр
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (14, 8);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (14, 9);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (15, 10);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (16, 11);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (17, 12);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (18, 13);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (15, 14);
INSERT INTO join_tracks_genres (track_id, genre_id) VALUES (18, 15);

--значения свойств
INSERT INTO app_track_features (id, creation_date, description, last_update, name, track_feature_type_id, track_id)
  VALUES (17, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'36', to_timestamp('2019-01-30', 'yyyy-mm-dd'), '36', 4, 14);
INSERT INTO app_track_features (id, creation_date, description, last_update, name, track_feature_type_id, track_id)
  VALUES (18, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'слово', to_timestamp('2019-01-30', 'yyyy-mm-dd'), 'слово', 5, 14);
INSERT INTO app_track_features (id, creation_date, description, last_update, name, track_feature_type_id, track_id)
  VALUES (19, to_timestamp('2019-01-05', 'yyyy-mm-dd'),'TRUE', to_timestamp('2019-01-30', 'yyyy-mm-dd'), 'TRUE', 6, 14);

-- пользователи
INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, is_approved, last_login_date, last_password_change_date, username, email, image_id, failed_password_attempt_count)
VALUES(20, to_timestamp('2019-01-30', 'yyyy-mm-dd'), current_timestamp, 'Билл Гейтс', '123456', 'Мне сказали, у меня скитлстрянка', 'Ты какой-то странный, Билли?', 3, FALSE, TRUE, to_timestamp('2019-03-30', 'yyyy-mm-dd'), to_timestamp('2019-02-18', 'yyyy-mm-dd'), 'billy', 'bolli@skittles.kz', 1, 0);
INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, is_approved, last_login_date, last_password_change_date, username, email, image_id, failed_password_attempt_count)
VALUES(21, to_timestamp('2019-05-11', 'yyyy-mm-dd'), current_timestamp, 'Иван Иванов', '123123', 'Я Бэтман', 'Кто ты?', 3, FALSE, TRUE, to_timestamp('2019-05-30', 'yyyy-mm-dd'), to_timestamp('2019-06-15', 'yyyy-mm-dd'), 'Ivan', 'ivan@gmail.com', 2, 0);
INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, is_approved, last_login_date, last_password_change_date, username, email, image_id, failed_password_attempt_count)
VALUES(22, to_timestamp('2019-06-11', 'yyyy-mm-dd'), current_timestamp, 'Марина', '654321', 'Отличная', 'Как погодка?', 3, TRUE, TRUE, to_timestamp('2019-06-12', 'yyyy-mm-dd'), to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'marina', 'marina@mail.ru', 3, 0);
INSERT INTO app_users (id, creation_date, last_update, name, password, password_answer, password_question, failed_password_answer_attempt_count, is_locked_out, is_approved, last_login_date, last_password_change_date, username, email, image_id, failed_password_attempt_count)
VALUES(23, to_timestamp('2019-06-11', 'yyyy-mm-dd'), current_timestamp, 'Вероника', '121212', 'Иванова', 'Девчья фамилия матери?', 3, FALSE, TRUE, to_timestamp('2019-06-12', 'yyyy-mm-dd'), to_timestamp('2019-05-01', 'yyyy-mm-dd'), 'nika', 'nika@protonmail.com', 4, 0);

--пользователь - группа
INSERT INTO join_users_artists (USER_ID, ARTIST_ID) values (20, 2);
INSERT INTO join_users_artists (USER_ID, ARTIST_ID) values (21, 3);
INSERT INTO join_users_artists (USER_ID, ARTIST_ID) values (22, 2);
INSERT INTO join_users_artists (USER_ID, ARTIST_ID) values (23, 1);

--пользователь-профиль
INSERT INTO app_user_profiles (id, creation_date, last_update, birth_date, city, country, email, first_name, hobby, last_name, mobile_phone, site, skype, user_id)
  VALUES (24, current_timestamp, current_timestamp, to_timestamp('1990-01-05', 'yyyy-mm-dd'), 'Москва', 'Россия', '111@222.ru', 'Билл', 'Прыжки с парашютом', 'Гейтс', '8-909-111-11-11', NULL, NULL, 20 );

--плейлисты
INSERT INTO app_playlists (id, creation_date, description, last_update, name, user_id, artist_id)
    VALUES (25, current_timestamp, 'Любимый плейлист Вероники', current_timestamp, 'хиты', 22, 1);
INSERT INTO app_playlists (id, creation_date, description, last_update, name, user_id, artist_id)
    VALUES (26, current_timestamp, 'Билл слушает на работе', current_timestamp, 'работа', 23, 1);
INSERT INTO app_playlists (id, creation_date, description, last_update, name, user_id, artist_id)
    VALUES (27, current_timestamp, 'Вилли слушает на природе', current_timestamp, 'Домашний', 23, 1);

--плейлист-трек
INSERT INTO join_playlists_tracks (track_id, playlist_id, position) VALUES(14,25,1);
INSERT INTO join_playlists_tracks (track_id, playlist_id, position) VALUES(15,25,2);
INSERT INTO join_playlists_tracks (track_id, playlist_id, position) VALUES(16,25,3);
INSERT INTO join_playlists_tracks (track_id, playlist_id, position) VALUES(15,26,1);
INSERT INTO join_playlists_tracks (track_id, playlist_id, position) VALUES(14,26,2);

--типы свойств плейлиста
INSERT INTO app_playlist_feature_types (id, description, name)
  VALUES(27, 'Строка', 'Свойство А');
INSERT INTO app_playlist_feature_types (id, description, name)
VALUES(28, 'BOOLEAN', 'Свойство Б') ;


--свойства плейлиста
INSERT INTO app_playlist_features (id, creation_date, description, last_update, name, playlist_id, playlist_feature_type_id)
  VALUES (29, current_timestamp, 'слово', current_timestamp, 'слово', 25, 27);
INSERT INTO app_playlist_features (id, creation_date, description, last_update, name, playlist_id, playlist_feature_type_id)
  VALUES (30, current_timestamp, 'TRUE', current_timestamp, 'TRUE', 25, 28);

--Статьи блога
INSERT INTO app_articles (id, creation_date, description, last_update, name, content, short_description, title, author_id)
  VALUES (31, current_timestamp, 'Описание', current_timestamp, NULL , 'слово слово слово слово слово слово слово слово слово слово слово слово слово', 'Краткое описание темы', 'Название 1', 20);
INSERT INTO app_articles (id, creation_date, description, last_update, name, content, short_description, title, author_id)
  VALUES (32, current_timestamp, 'Описание описание', current_timestamp, NULL , 'Предложение предложение предложение предложение', NULL , 'Название 2', 20);

--Комментарии к статье
INSERT INTO app_comments (id, creation_date, description, last_update, name, comment_content, entity, entity_id, author_id)
  VALUES (33, current_timestamp, '', current_timestamp, 'Название комментария 1', 'слово ', 'TRACK', 31, 22);
INSERT INTO app_comments (id, creation_date, description, last_update, name, comment_content, entity, entity_id, author_id)
  VALUES (34, current_timestamp, '', current_timestamp, 'Название комментария 2', 'Предложение предложение', 'TRACK', 31, 22);

--роли
INSERT INTO app_roles (id, name) VALUES (35, 'ADMIN');
INSERT INTO app_roles (id, name) VALUES (36, 'USER');
INSERT INTO app_roles (id, name) VALUES (37, 'ARTIST');
INSERT INTO app_roles (id, name) VALUES (38, 'SUPERADMIN');

--пользователь-роль
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(20, 35);
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(20, 37);
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(21, 36);
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(22, 36);
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(23, 36);
INSERT INTO join_users_roles (user_id, role_id)
    VALUES(23, 37);

--лайки
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (1, current_timestamp, 20, 'TRACK', 14);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (2, current_timestamp, 21, 'TRACK', 14);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (3, current_timestamp, 20, 'TRACK', 15);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (4, current_timestamp, 22, 'TRACK', 14);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (5, current_timestamp, 20, 'TRACK', 18);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (6, current_timestamp, 23, 'TRACK', 16);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (7, current_timestamp, 20, 'TRACK', 16);
INSERT INTO app_likes (id, creation_date, user_id, entity, entity_id) VALUES (8, current_timestamp, 23, 'TRACK', 14);

--плейлист-жанр
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (25, 8);
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (25, 9);
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (26, 10);
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (26, 11);
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (27, 12);
INSERT INTO join_playlists_genres (playlist_id, genre_id) VALUES (27, 13);

--исполнитель-жанр
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (1, 8);
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (2, 9);
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (3, 10);
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (1, 11);
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (2, 12);
INSERT INTO join_artists_genres (artist_id, genre_id) VALUES (3, 13);