--liquibase formatted sql

--changeset paramonov:1
ALTER TABLE comment
    ADD COLUMN news_id BIGSERIAL NOT NULL REFERENCES news;


ALTER TABLE comment
    ADD COLUMN user_id BIGSERIAL NOT NULL REFERENCES users;

