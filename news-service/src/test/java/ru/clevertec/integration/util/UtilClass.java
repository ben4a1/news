package ru.clevertec.integration.util;

import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.model.Role;

public class UtilClass {

    public static News news1 = News.builder().id(1L).build();
    public static News news2 = News.builder().id(2L).build();
    public static News news3 = News.builder().id(3L).build();
    public static News news4 = News.builder().id(4L).build();

    public static User username1Admin = User.builder().id(1L).username("username1").role(Role.ADMIN).build();
    public static User username2Journalist = User.builder().id(2L).username("username2").role(Role.JOURNALIST).build();
    public static User username3Journalist = User.builder().id(3L).username("username3").role(Role.JOURNALIST).build();
    public static User username4Subscriber = User.builder().id(4L).username("username4").role(Role.SUBSCRIBER).build();

    public static Comment comment1 = Comment.builder().id(1L).subject("subject")
            .user(username1Admin)
            .news(news1)
            .build();
    public static Comment comment2 = Comment.builder().id(2L).subject("subject")
            .user(username2Journalist)
            .news(news1)
            .build();
    public static Comment comment3 = Comment.builder().id(3L).subject("subject")
            .user(username3Journalist)
            .news(news1)
            .build();
    public static Comment comment4 = Comment.builder().id(4L).subject("subject")
            .user(username1Admin)
            .news(news2)
            .build();
    public static Comment comment5 = Comment.builder().id(5L).subject("subject")
            .user(username4Subscriber)
            .news(news2)
            .build();
    public static Comment comment6 = Comment.builder().id(6L).subject("subject")
            .user(username1Admin)
            .news(news3)
            .build();
    public static Comment comment7 = Comment.builder().id(7L).subject("subject")
            .user(username3Journalist)
            .news(news4)
            .build();
    public static Comment comment8 = Comment.builder().id(8L).subject("subject")
            .user(username4Subscriber)
            .news(news4)
            .build();
}
