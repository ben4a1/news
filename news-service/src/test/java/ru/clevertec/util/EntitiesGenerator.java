package ru.clevertec.util;

import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.model.Role;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class EntitiesGenerator {

    public static final Long NEWS_ID = 1L;
    public static final Long COMMENT_ID = 1L;
    public static final Long USER_ID = 1L;
    public static final String USERNAME = "username1";
    public final static String SUBJECT = "subject";
    public static final String TITLE = "title";
    public static final String PASSWORD = "password";
    public static final LocalDateTime CREATION_TIME = LocalDateTime.of(2022, 10, 9, 12, 12);
    public static final LocalDateTime NOW = now();

    public static News getNews() {
        return News.builder()
                .id(NEWS_ID)
                .creationTime(CREATION_TIME)
                .title(TITLE)
                .subject(SUBJECT)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .id(USER_ID)
                .username(USERNAME)
                .password(PASSWORD)
                .role(Role.ADMIN)
                .build();
    }

    public static Comment getComment() {
        return Comment.builder().id(COMMENT_ID)
                .subject(SUBJECT)
                .user(getUser())
                .creationTime(CREATION_TIME)
                .news(getNews())
                .build();
    }
}
