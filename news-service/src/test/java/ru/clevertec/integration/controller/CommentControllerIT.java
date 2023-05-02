package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.model.Role;
import ru.clevertec.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

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
    private final CommentRepository commentRepository;

    @Test
    void findAll() {
    }

    @Test
    void checkFindByIdShouldReturnUsername1() {
        var actualResult = commentController.findById(COMMENT_ID).username();
        Comment expectedComment = COMMENTS.get(Math.toIntExact(COMMENT_ID - 1));
        CommentReadMapper commentReadMapper = new CommentReadMapper();
        var expectedResult = commentReadMapper.map(expectedComment).username();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSizeBy1() {
        int startSize = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);
        CommentReadDto commentReadDto = commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();
        assertThat(startSize).isEqualTo(sizeAfter - 1);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}