--liquibase formatted sql

--changeset paramonov:1
DROP TABLE IF EXISTS news, comment, users CASCADE;

CREATE TABLE IF NOT EXISTS news
(
    news_id       BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    subject       VARCHAR   NOT NULL,
    title         VARCHAR   NOT NULL
);
--rollback DROP TABLE news CASCADE

--changeset paramonov:2
CREATE TABLE IF NOT EXISTS comment
(
    comment_id    BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    subject       VARCHAR   NOT NULL
);
--rollback DROP TABLE comment CASCADE

--changeset paramonov:3
CREATE TABLE IF NOT EXISTS users
(
    user_id  BIGSERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role     VARCHAR NOT NULL
);
--rollback DROP TABLE users CASCADE