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

CREATE TABLE "app_albums" (
 "id" int8 NOT NULL,
 "creation_date" timestamp(6) NOT NULL,
 "description" varchar(255) COLLATE "default",
 "last_update" timestamp(6) NOT NULL,
 "name" varchar(255) COLLATE "default",
 "artist_id" int8,
 "image_id" int8,
 CONSTRAINT "app_albums_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_albums" OWNER TO "musicportal";

CREATE TABLE "app_album_features" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"value" varchar(255) COLLATE "default",
"album_id" int8,
"album_feature_type_id" int8,
CONSTRAINT "app_album_feature_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_album_features" OWNER TO "musicportal";

CREATE TABLE "app_album_feature_types" (
"id" int8 NOT NULL,
"description" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
CONSTRAINT "app_album_feature_type_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

CREATE TABLE "join_albums_genres" (
"album_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_albums_genres" OWNER TO "musicportal";

CREATE TABLE "join_albums_tracks" (
"album_id" int8 NOT NULL,
"track_id" int8 NOT NULL,
"position" int
)
WITHOUT OIDS;

ALTER TABLE "join_albums_tracks" OWNER TO "musicportal";


ALTER TABLE "app_playlist_feature_types" OWNER TO "musicportal";
ALTER TABLE "app_likes" ADD CONSTRAINT "fk_likes_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_albums" ADD CONSTRAINT "fk_albums_artist_artist" FOREIGN KEY ("artist_id") REFERENCES "app_artists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_albums" ADD CONSTRAINT "fk_albums_image_image" FOREIGN KEY ("image_id") REFERENCES "app_images" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_album_features" ADD CONSTRAINT "fk_album_feature_album" FOREIGN KEY ("album_id") REFERENCES "app_albums" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_album_features" ADD CONSTRAINT "fk_album_feature_album_feature_type" FOREIGN KEY ("album_feature_type_id") REFERENCES "app_album_feature_types" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE "join_users_artists" ADD CONSTRAINT "fk_join_users_artists_artist" FOREIGN KEY ("artist_id") REFERENCES "app_artists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_users_artists" ADD CONSTRAINT "fk_join_users_artists_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlists_genres_playlist" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlists_genres" ADD CONSTRAINT "fk_join_playlists_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_artists_genres" ADD CONSTRAINT "fk_join_artists_genres_artist" FOREIGN KEY ("artist_id") REFERENCES "app_artists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_artists_genres" ADD CONSTRAINT "fk_join_artists_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_albums_genres" ADD CONSTRAINT "fk_join_albums_genres_album" FOREIGN KEY ("album_id") REFERENCES "app_albums" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_albums_genres" ADD CONSTRAINT "fk_join_albums_genres_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_albums_tracks" ADD CONSTRAINT "fk_join_albums_tracks_album" FOREIGN KEY ("album_id") REFERENCES "app_albums" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_albums_tracks" ADD CONSTRAINT "fk_join_albums_tracks_track" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

