--liquibase formatted sql

--changeset paramonov:1
CREATE TABLE IF NOT EXISTS news
(
    id            BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP     NOT NULL,
    subject       VARCHAR(1000) NOT NULL,
    title         VARCHAR(128)  NOT NULL
);
--rollback DROP TABLE news CASCADE

--changeset paramonov:2
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(64)  NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role     VARCHAR(32)  NOT NULL
);
--rollback DROP TABLE users CASCADE

--changeset paramonov:3
CREATE TABLE IF NOT EXISTS comment
(
    id            BIGSERIAL PRIMARY KEY,
    creation_time TIMESTAMP    NOT NULL,
    subject       VARCHAR(256) NOT NULL,
    news_id       BIGSERIAL    NOT NULL REFERENCES news,
    user_id       BIGSERIAL    NOT NULL REFERENCES users
);
--rollback DROP TABLE comment CASCADE

--changeset paramonov:4
CREATE TABLE news_comments
(
    news_id     BIGINT NOT NULL REFERENCES news,
    comments_id BIGINT NOT NULL UNIQUE REFERENCES comment
);
--rollback DROP TABLE news_comments CASCADE



