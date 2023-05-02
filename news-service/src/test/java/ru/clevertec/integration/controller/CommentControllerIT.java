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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    public static final Long COMMENT_ID = 1L;
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
    private final CommentReadMapper commentReadMapper;

    @Test
    void findAll() {
        int expected = commentRepository.findAll().size();
        int actual = commentController.findAll().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnUsername1() {
        var actualResult = commentController.findById(COMMENT_ID).username();
        Comment expectedComment = COMMENTS.get(Math.toIntExact(COMMENT_ID - 1));
        var expectedResult = commentReadMapper.map(expectedComment).username();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        int startSize = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);
        CommentReadDto commentReadDto = commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();
        assertThat(startSize).isLessThan(sizeAfter);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        Optional<Comment> optionalComment = commentRepository.findById(2L);
        Comment comment = optionalComment.orElse(null);
        assert comment != null;
        String subjectBeforeUpdate = comment.getSubject();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("super", "username1", 2L, 2L);
        commentController.update(2L, commentCreateUpdateDto);
        Optional<Comment> optionalCommentAfterUpdate = commentRepository.findById(2L);
        Comment commentAfterUpdate = optionalCommentAfterUpdate.orElse(null);
        assert commentAfterUpdate != null;
        String subjectAfterUpdate = commentAfterUpdate.getSubject();
        assertThat(subjectBeforeUpdate).isNotEqualTo(subjectAfterUpdate);
    }

    @Test
    void checkDeleteShouldReturnDecreasedSize() {
        int sizeBefore = commentRepository.findAll().size();
        commentController.delete(1L);
        commentController.delete(2L);
        int sizeAfter = commentRepository.findAll().size();
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }
}