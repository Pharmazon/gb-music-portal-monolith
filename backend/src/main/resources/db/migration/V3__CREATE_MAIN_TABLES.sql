CREATE TABLE IF NOT EXISTS app_likes(
    ID int8 PRIMARY KEY,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    USER_ID int8 NOT NULL ,
    ENTITY VARCHAR(32),
    ENTITY_ID int8 NOT NULL,
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES app_users (ID),
    UNIQUE (USER_ID, ENTITY, ENTITY_ID)
);

CREATE TABLE "join_users_artists" (
"user_id" int8 NOT NULL,
"artist_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_users_artists" OWNER TO "musicportal";

CREATE TABLE "join_playlists_genres" (
"playlist_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_playlists_genres" OWNER TO "musicportal";

CREATE TABLE "join_artists_genres" (
"artist_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_artists_genres" OWNER TO "musicportal";

ALTER TABLE "app_likes" ADD CONSTRAINT "fk_likes_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_users_artists" ADD CONSTRAINT "fk_join_users_artists_artist" FOREIGN KEY ("artist_id") REFERENCES "app_artists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_users_artists" ADD CONSTRAINT "fk_join_users_artists_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlists_genres_playlist" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlists_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_artists_genres" ADD CONSTRAINT "fk_join_artists_genres_artist" FOREIGN KEY ("artist_id") REFERENCES "app_artists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_artists_genres" ADD CONSTRAINT "fk_join_artists_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

