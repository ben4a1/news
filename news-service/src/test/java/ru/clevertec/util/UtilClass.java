package ru.clevertec.util;

import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.model.Role;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

public class UtilClass {

    private final static String SUBJECT = "subject";
    private static final String TITLE = "title";
    private static final String PASSWORD = "password";
    public static News news1 = News.builder().id(1L).creationTime(now()).title(TITLE).subject(SUBJECT).build();
    public static News news2 = News.builder().id(2L).creationTime(now()).title(TITLE).subject(SUBJECT).build();
    public static News news3 = News.builder().id(3L).creationTime(now()).title(TITLE).subject(SUBJECT).build();
    public static News news4 = News.builder().id(4L).creationTime(now()).title(TITLE).subject(SUBJECT).build();

    public static User username1Admin = User.builder().id(1L).username("username1").password(PASSWORD).role(Role.ADMIN).build();
    public static User username2Journalist = User.builder().id(2L).username("username2").password(PASSWORD).role(Role.JOURNALIST).build();
    public static User username3Journalist = User.builder().id(3L).username("username3").password(PASSWORD).role(Role.JOURNALIST).build();
    public static User username4Subscriber = User.builder().id(4L).username("username4").password(PASSWORD).role(Role.SUBSCRIBER).build();

    public static Comment comment1 = Comment.builder().id(1L).subject(SUBJECT)
            .user(username1Admin)
            .creationTime(now())
            .news(news1)
            .build();
    public static Comment comment2 = Comment.builder().id(2L).subject(SUBJECT)
            .user(username2Journalist)
            .creationTime(now())
            .news(news1)
            .build();
    public static Comment comment3 = Comment.builder().id(3L).subject(SUBJECT)
            .user(username3Journalist)
            .creationTime(now())
            .news(news1)
            .build();
    public static Comment comment4 = Comment.builder().id(4L).subject(SUBJECT)
            .user(username1Admin)
            .creationTime(now())
            .news(news2)
            .build();
    public static Comment comment5 = Comment.builder().id(5L).subject(SUBJECT)
            .user(username4Subscriber)
            .creationTime(now())
            .news(news2)
            .build();
    public static Comment comment6 = Comment.builder().id(6L).subject(SUBJECT)
            .user(username1Admin)
            .creationTime(now())
            .news(news3)
            .build();
    public static Comment comment7 = Comment.builder().id(7L).subject(SUBJECT)
            .user(username3Journalist)
            .creationTime(now())
            .news(news4)
            .build();
    public static Comment comment8 = Comment.builder().id(8L).subject(SUBJECT)
            .user(username4Subscriber)
            .creationTime(now())
            .news(news4)
            .build();

    public static List<Comment> listOf8Comments() {
        return new ArrayList<>(List.of(comment1, comment2, comment3, comment4, comment5, comment6, comment7, comment8));
    }
}
