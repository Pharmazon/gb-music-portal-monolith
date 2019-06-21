CREATE TABLE IF NOT EXISTS ROLES(
    ID NUMERIC(38,0) PRIMARY KEY ,
    LAST_UPDATE TIMESTAMP(6) NOT NULL ,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    ROLE_NAME VARCHAR(32) NOT NULL ,
    DEFINITION VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS USER_ROLES(
    ID NUMERIC(38,0) PRIMARY KEY ,
    USER_ID NUMERIC(38,0) NOT NULL ,
    ROLE_ID NUMERIC(38,0) NOT NULL ,
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    CONSTRAINT FK_ROLE FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ID),
    UNIQUE (USER_ID, ROLE_ID)
);

CREATE TABLE IF NOT EXISTS ADDITIONAL_USER_DATA(
    ID NUMERIC(38,0) PRIMARY KEY ,
    LAST_UPDATE TIMESTAMP(6) NOT NULL ,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    USER_ID NUMERIC(38,0) NOT NULL ,
    NAME VARCHAR(32),
    SECOND_NAME VARCHAR(32),
    LAST_NAME VARCHAR(32),
    BIRTH_DATE TIMESTAMP(6),
    START_CAREER TIMESTAMP(6),
    BIOGRAPHY VARCHAR(4000),
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE IF NOT EXISTS LIKES(
    ID NUMERIC(38,0) PRIMARY KEY,
    LAST_UPDATE TIMESTAMP(6) NOT NULL ,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    USER_ID NUMERIC(38,0) NOT NULL ,
    ENTITY VARCHAR(32),
    ENTITY_ID NUMERIC(38,0) NOT NULL,
    IS_DELETED NUMERIC(1,0) NOT NULL,
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
    UNIQUE (USER_ID, ENTITY_ID)
);

CREATE TABLE IF NOT EXISTS COMMENTS(
    ID NUMERIC(38,0) PRIMARY KEY,
    LAST_UPDATE TIMESTAMP(6) NOT NULL ,
    CREATION_DATE TIMESTAMP(6) NOT NULL ,
    USER_ID NUMERIC(38,0) NOT NULL ,
    ENTITY VARCHAR(32),
    ENTITY_ID NUMERIC(38,0) NOT NULL,
    COMMENT VARCHAR(4000) NOT NULL ,
    IS_DELETED NUMERIC(1,0) NOT NULL,
    CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

CREATE TABLE IF NOT EXISTS ART_GROUP_TRACK(
    ID NUMERIC(38,0) PRIMARY KEY ,
    ART_GROUP_ID NUMERIC(38,0) NOT NULL ,
    TRACK_ID NUMERIC(38,0) NOT NULL ,
    CONSTRAINT FK_GROUP FOREIGN KEY (ART_GROUP_ID) REFERENCES art_group(ID),
    CONSTRAINT FK_TRACK FOREIGN KEY (TRACK_ID) REFERENCES track(ID),
    UNIQUE (ART_GROUP_ID, TRACK_ID)
)