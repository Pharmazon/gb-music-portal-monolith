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

CREATE TABLE "app_categories" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"parent_id" int8,
CONSTRAINT "app_categories_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_categories" OWNER TO "musicportal";

CREATE TABLE "app_comments" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"comment_content" varchar(255) COLLATE "default",
"article_id" int8,
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
"track_feature_type_id" int8,
"track_id" int8,
CONSTRAINT "app_track_feature_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_track_features" OWNER TO "musicportal";

CREATE TABLE "app_track_feature_types" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
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
"user_id" int8,
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
"playlist_id" int8,
"playlist_feature_type_id" int8,
CONSTRAINT "app_playlist_feature_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_playlist_features" OWNER TO "musicportal";

CREATE TABLE "app_playlist_feature_types" (
"id" int8 NOT NULL,
"creation_date" timestamp(6) NOT NULL,
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
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

ALTER TABLE "app_track" OWNER TO "musicportal";

CREATE TABLE "app_user_membership" (
"id" int8 NOT NULL,
CONSTRAINT "app_user_membership_pkey" PRIMARY KEY ("id")
)
WITHOUT OIDS;

ALTER TABLE "app_user_membership" OWNER TO "musicportal";

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
"description" varchar(255) COLLATE "default",
"last_update" timestamp(6) NOT NULL,
"name" varchar(255) COLLATE "default",
"password" varchar(255) COLLATE "default" NOT NULL,
"password_answer" varchar(255) COLLATE "default",
"password_format" varchar(255) COLLATE "default",
"password_question" varchar(255) COLLATE "default",
"password_salt" varchar(255) COLLATE "default",
"comment" varchar(255) COLLATE "default",
"create_date" timestamp(6),
"failed_password_answer_attempt_count" int4,
"failed_password_answer_attempt_window_start" timestamp(6),
"failed_password_attempt_count" int4,
"failed_password_attempt_window_start" timestamp(6),
"is_approved" bool,
"is_locked_out" bool,
"last_lockout_date" timestamp(6),
"last_login_date" timestamp(6),
"last_password_change_date" timestamp(6),
"username" varchar(255) COLLATE "default" NOT NULL,
"bands_id" int8,
CONSTRAINT "app_users_pkey" PRIMARY KEY ("id") ,
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

CREATE TABLE "join_track_category" (
"track_id" int8 NOT NULL,
"category_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_track_category" OWNER TO "musicportal";

CREATE TABLE "join_user_roles" (
"user_id" int8 NOT NULL,
"role_id" int8 NOT NULL
)
WITHOUT OIDS;

ALTER TABLE "join_user_roles" OWNER TO "musicportal";


ALTER TABLE "app_articles" ADD CONSTRAINT "fkhh9xjflw54gvm8kv8mdqarwf3" FOREIGN KEY ("author_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_categories" ADD CONSTRAINT "fkbyuv0afi2o75o9uvkbmle5y63" FOREIGN KEY ("parent_id") REFERENCES "app_categories" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_comments" ADD CONSTRAINT "fkl8dlgfrr6i0bk4bgpq3r6ek3" FOREIGN KEY ("article_id") REFERENCES "

" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_comments" ADD CONSTRAINT "fkcalvpfxee5tgosf8sxfdwqdvp" FOREIGN KEY ("id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_track_features" ADD CONSTRAINT "fknw9lsddmws4km80jme1yhtg38" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_track_features" ADD CONSTRAINT "fkjcvrnftutablp8n9dcjakp5bk" FOREIGN KEY ("track_feature_type_id") REFERENCES "app_track_feature_types" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlists" ADD CONSTRAINT "fkd0nh0xmgbaq40wa30hcq20yk5" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlist_features" ADD CONSTRAINT "fkhns7vvqehcrprnvg9imny6vni" FOREIGN KEY ("playlist_feature_type_id") REFERENCES "app_playlist_feature_types" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_playlist_features" ADD CONSTRAINT "fkd6xl0kes68ksb9lash61pjmsj" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_tracks" ADD CONSTRAINT "fk9tiaeruhtv5lth1ofc9tlculi" FOREIGN KEY ("band_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_user_membership" ADD CONSTRAINT "fkhtihn5n9vnceac9e0ql7cufea" FOREIGN KEY ("id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_user_profiles" ADD CONSTRAINT "fk5qcfn5fkrje272fup8i559tso" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "app_users" ADD CONSTRAINT "fkachy3vcme8p0eef7ly2x96rg4" FOREIGN KEY ("bands_id") REFERENCES "app_bands" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlist_track" ADD CONSTRAINT "fksa71x4knksydhbagnk52duc3q" FOREIGN KEY ("playlist_id") REFERENCES "app_playlists" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_playlist_track" ADD CONSTRAINT "fklfdk6xr5fjww6golmkl1rpqr2" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_track_category" ADD CONSTRAINT "fkch26sawigosjm9ctqd6te8b7x" FOREIGN KEY ("category_id") REFERENCES "app_categories" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_track_category" ADD CONSTRAINT "fk1d2rau3dcxnl6caffx5rv00km" FOREIGN KEY ("track_id") REFERENCES "app_tracks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_roles" ADD CONSTRAINT "fke9fjrjnmtpwunxmcob6axpy7v" FOREIGN KEY ("role_id") REFERENCES "app_roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "join_user_roles" ADD CONSTRAINT "fkhvrvspq4nallqheld0hq8x4g0" FOREIGN KEY ("user_id") REFERENCES "app_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
