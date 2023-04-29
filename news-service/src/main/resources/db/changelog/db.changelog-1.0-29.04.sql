--liquibase formatted sql

--changeset paramonov:1
DROP TABLE IF EXISTS news, comment, users CASCADE;

CREATE TABLE IF NOT EXISTS news
(
    news_id       BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    subject       VARCHAR(1000)   NOT NULL,
    title         VARCHAR(128)   NOT NULL
);
--rollback DROP TABLE news CASCADE

--changeset paramonov:2
CREATE TABLE IF NOT EXISTS comment
(
    comment_id    BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    subject       VARCHAR(256)   NOT NULL
);
--rollback DROP TABLE comment CASCADE

--changeset paramonov:3
CREATE TABLE IF NOT EXISTS users
(
    user_id  BIGSERIAL PRIMARY KEY,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(256) NOT NULL,
    role     VARCHAR(32) NOT NULL
);
--rollback DROP TABLE users CASCADE