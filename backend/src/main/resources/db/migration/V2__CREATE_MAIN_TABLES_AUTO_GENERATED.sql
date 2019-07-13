CREATE TABLE "app_articles" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"content" varchar(255) COLLATE "default",
"short_description" varchar(255) COLLATE "default",
"title" varchar(255) COLLATE "default",
"author_id" int8,
CONSTRAINT "app_articles_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_articles" OWNER TO "musicportal";

CREATE TABLE "app_genres" (
"id" int8 NOT NULL,
"name" varchar(255) COLLATE "default",
CONSTRAINT "app_genres_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_genres" OWNER TO "musicportal";

CREATE TABLE "app_comments" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"comment_content" varchar(255) COLLATE "default",
"author_id" int8,
"entity_id" int8,
CONSTRAINT "app_comments_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_comments" OWNER TO "musicportal";

CREATE TABLE "app_track_features" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"value" varchar(255) COLLATE "default",
"track_feature_type_id" int8,
"track_id" int8,
CONSTRAINT "app_track_feature_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_track_features" OWNER TO "musicportal";

CREATE TABLE "app_track_feature_types" (
"id" int8 NOT NULL,
"description" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
CONSTRAINT "app_track_feature_type_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_track_feature_types" OWNER TO "musicportal";

CREATE TABLE "app_bands" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"image_id" int8,
CONSTRAINT "app_bands_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_bands" OWNER TO "musicportal";

CREATE TABLE "app_playlists" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"band_id" int8,
"user_id" int8,
"image_id" int8,
CONSTRAINT "app_playlists_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_playlists" OWNER TO "musicportal";

CREATE TABLE "app_playlist_features" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"value" varchar(255) COLLATE "default",
"playlist_id" int8,
"playlist_feature_type_id" int8,
CONSTRAINT "app_playlist_feature_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_playlist_features" OWNER TO "musicportal";

CREATE TABLE "app_playlist_feature_types" (
"id" int8 NOT NULL,
"description" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
CONSTRAINT "app_playlist_feature_type_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_playlist_feature_types" OWNER TO "musicportal";

CREATE TABLE "app_roles" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
CONSTRAINT "app_roles_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_roles" OWNER TO "musicportal";

CREATE TABLE "app_tracks" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"link" varchar(255) COLLATE "default",
"band_id" int8,
CONSTRAINT "app_track_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_tracks" OWNER TO "musicportal";

CREATE TABLE "app_user_profiles" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"birth_date" timestamp(6),
"city" varchar(255) COLLATE "default",
"country" varchar(255) COLLATE "default",
"email" varchar(255) COLLATE "default",
"first_name" varchar(255) COLLATE "default",
"hobby" varchar(255) COLLATE "default",
"last_name" varchar(255) COLLATE "default",
"mobile_phone" varchar(255) COLLATE "default",
"site" varchar(255) COLLATE "default",
"skype" varchar(255) COLLATE "default",
"user_id" int8,
CONSTRAINT "app_user_profiles_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_user_profiles" OWNER TO "musicportal";

CREATE TABLE "app_users" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"password" varchar(255) COLLATE "default" NOT NULL,
"password_answer" varchar(255) COLLATE "default",
"password_question" varchar(255) COLLATE "default",
"failed_password_answer_attempt_count" int4,
"is_locked_out" bool NOT NULL,
"last_login_date" timestamp(6),
"last_password_change_date" timestamp(6),
"username" varchar(255) COLLATE "default" NOT NULL,
"email" varchar(255) COLLATE "default" NOT NULL,
"description" varchar(255) COLLATE "default",
"comment" varchar(255) COLLATE "default",
"failed_password_answer_attempt_window_start" timestamp(6),
"failed_password_attempt_count" int4,
"failed_password_attempt_window_start" timestamp(6),
"is_approved" bool NOT NULL,
"last_lockout_date" timestamp(6),
"password_format" varchar(255) COLLATE "default",
"password_salt" varchar(255) COLLATE "default",
"image_id" int8,
CONSTRAINT "app_users_pkey" PRIMARY KEY ("id") ,
CONSTRAINT "uk_4vj92ux8a2eehds1mdvmks473" UNIQUE ("email"),
CONSTRAINT "uk_spsnwr241e9k9c8p5xl4k45ih" UNIQUE ("username")
)
WITHOUT OIDS;

ALTER TABLE "app_users" OWNER TO "musicportal";

CREATE TABLE "join_playlist_track" (
"track_id" int8 NOT NULL,
"playlist_id" int8 NOT NULL,
"position" int
)
WITHOUT OIDS;

ALTER TABLE "join_playlist_track" OWNER TO "musicportal";

CREATE TABLE "join_tracks_genres" (
"track_id" int8 NOT NULL,
"genre_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_tracks_genres" OWNER TO "musicportal";

CREATE TABLE "join_user_roles" (
"user_id" int8 NOT NULL,
"role_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_user_roles" OWNER TO "musicportal";

CREATE TABLE "app_images" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"img_link" varchar(255) COLLATE "default",
CONSTRAINT "app_images_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_images" OWNER TO "musicportal";

ALTER TABLE "app_articles" ADD CONSTRAINT "fk_articles_author" FOREIGN KEY ("author_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_bands" ADD CONSTRAINT "fk_band_image" FOREIGN KEY ("image_id") REFERENCES "app_images" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_comments" ADD CONSTRAINT "fk_comment_user" FOREIGN KEY ("author_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_track_features" ADD CONSTRAINT "fk_feature_track" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_track_features" ADD CONSTRAINT "fk_tr_feature_tr_type" FOREIGN KEY ("track_feature_type_id") REFERENCES "app_track_feature_types" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlists" ADD CONSTRAINT "fk_playlist_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlists" ADD CONSTRAINT "fk_playlist_image" FOREIGN KEY ("image_id") REFERENCES "app_images" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlists" ADD CONSTRAINT "fk_playlists_band" FOREIGN KEY ("band_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlist_features" ADD CONSTRAINT "fk_pl_feature_f_type" FOREIGN KEY ("playlist_feature_type_id") REFERENCES "app_playlist_feature_types" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlist_features" ADD CONSTRAINT "fk_feature_playlist" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_tracks" ADD CONSTRAINT "fk_track_band" FOREIGN KEY ("band_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_user_profiles" ADD CONSTRAINT "fk_profile_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_users" ADD CONSTRAINT "fk4kkl36xb5rkenws4hr2dw98xq" FOREIGN KEY ("image_id") REFERENCES "app_images" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlist_track" ADD CONSTRAINT "fk_playlist" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlist_track" ADD CONSTRAINT "fk_track" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_tracks_genres" ADD CONSTRAINT "fk_genre" FOREIGN KEY ("genre_id") REFERENCES "app_genres" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_tracks_genres" ADD CONSTRAINT "fk_track" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_roles" ADD CONSTRAINT "fk_role" FOREIGN KEY ("role_id") REFERENCES "app_roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_roles" ADD CONSTRAINT "fk_user" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
