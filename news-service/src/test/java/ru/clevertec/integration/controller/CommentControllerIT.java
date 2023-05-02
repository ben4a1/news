package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.model.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    public static final Long COMMENT_ID = 1L;
    public static final LocalDateTime CREATION_TIME = now();
    public static final String SUBJECT = "Wow, what a news!?";
    private static final String USERNAME = "Max";
    private static final List<Comment> COMMENTS = new ArrayList<>();

    static {
        COMMENTS.add(Comment.builder().id(1L).subject("subject")
                .user(User.builder().id(1L).username("username1").role(Role.ADMIN).build())
                .news(News.builder().id(1L).build())
                .build());
        COMMENTS.add(Comment.builder().id(2L).subject("subject")
                .user(User.builder().id(2L).username("username2").role(Role.JOURNALIST).build())
                .news(News.builder().id(1L).build())
                .build());
        COMMENTS.add(Comment.builder().id(3L).subject("subject")
                .user(User.builder().id(3L).username("username3").role(Role.JOURNALIST).build())
                .news(News.builder().id(1L).build())
                .build());
        COMMENTS.add(Comment.builder().id(4L).subject("subject")
                .user(User.builder().id(1L).username("username1").role(Role.ADMIN).build())
                .news(News.builder().id(2L).build())
                .build());
        COMMENTS.add(Comment.builder().id(5L).subject("subject")
                .user(User.builder().id(4L).username("username4").role(Role.SUBSCRIBER).build())
                .news(News.builder().id(2L).build())
                .build());
        COMMENTS.add(Comment.builder().id(6L).subject("subject")
                .user(User.builder().id(1L).username("username1").role(Role.ADMIN).build())
                .news(News.builder().id(3L).build())
                .build());
        COMMENTS.add(Comment.builder().id(7L).subject("subject")
                .user(User.builder().id(3L).username("username3").role(Role.JOURNALIST).build())
                .news(News.builder().id(4L).build())
                .build());
        COMMENTS.add(Comment.builder().id(8L).subject("subject")
                .user(User.builder().id(4L).username("username4").role(Role.SUBSCRIBER).build())
                .news(News.builder().id(4L).build())
                .build());
    }

    private final CommentController commentController;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        CommentReadDto actualResult = commentController.findById(COMMENT_ID);
        Comment expectedComment = COMMENTS.get(Math.toIntExact(COMMENT_ID - 1));
        CommentReadMapper commentReadMapper = new CommentReadMapper();
        CommentReadDto expectedResult = commentReadMapper.map(expectedComment);
        Assertions.assertThat(actualResult.username()).isEqualTo(expectedResult.username());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}