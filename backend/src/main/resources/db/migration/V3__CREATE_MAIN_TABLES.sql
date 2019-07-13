CREATE TABLE IF NOT EXISTS app_likes(
    ID int8 PRIMARY KEY,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    USER_ID int8 NOT NULL ,
    ENTITY VARCHAR(32),
    ENTITY_ID int8 NOT NULL,
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES app_users (ID),
    UNIQUE (USER_ID, ENTITY, ENTITY_ID)
);

CREATE TABLE "join_user_bands" (
"user_id" int8 NOT NULL,
"band_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_user_bands" OWNER TO "musicportal";

CREATE TABLE "join_playlists_genres" (
"playlist_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_playlists_genres" OWNER TO "musicportal";

CREATE TABLE "join_bands_genres" (
"band_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_bands_genres" OWNER TO "musicportal";

ALTER TABLE "app_likes" ADD CONSTRAINT "fk_likes_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_bands" ADD CONSTRAINT "fk_join_user_bands_band" FOREIGN KEY ("band_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_bands" ADD CONSTRAINT "fk_join_user_bands_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlist_genres_playlist" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlist_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_bands_genres" ADD CONSTRAINT "fk_join_bands_genres_band" FOREIGN KEY ("band_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_bands_genres" ADD CONSTRAINT "fk_join_bands_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

