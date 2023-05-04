package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.util.UtilClass.SUBJECT;

/**
 * sql/data.sql : 4 News, 4 Users, 8 Comments
 */
@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    private final CommentController commentController;
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Test
    void checkFindAllShouldReturnSameSize() {
        int expected = commentRepository.findAll().size();

        int actual = commentController.findAll().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnSameUsername() {
        Comment comment = getComment();
        String expectedResult = comment.getUser().getUsername();
        Comment savedComment = commentRepository.save(comment);
        Long commentId = savedComment.getId();

        var actualResult = commentController.findById(commentId).username();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        int sizeBefore = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);

        commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        Optional<Comment> optionalComment = commentRepository.findById(2L);
        Comment comment = optionalComment.orElse(null);
        assertThat(comment).isNotNull();
        String subjectBeforeUpdate = comment.getSubject();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("super", "username1", 2L, 2L);

        commentController.update(2L, commentCreateUpdateDto);
        Optional<Comment> optionalCommentAfterUpdate = commentRepository.findById(2L);
        Comment commentAfterUpdate = optionalCommentAfterUpdate.orElse(null);
        assertThat(commentAfterUpdate).isNotNull();
        String subjectAfterUpdate = commentAfterUpdate.getSubject();

        assertThat(subjectBeforeUpdate).isNotEqualTo(subjectAfterUpdate);
    }

    @Test
    void checkDeleteShouldReturnDecreasedSize() {
        int sizeBefore = commentRepository.findAll().size();

        commentController.delete(1L);
        commentController.delete(2L);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeAfter).isLessThan(sizeBefore);
    }

    private Comment getComment() {
        return Comment.builder().subject(SUBJECT)
                .user(userRepository.findAll().stream().findAny().orElse(null))
                .creationTime(now())
                .news(newsRepository.findAll().stream().findAny().orElse(null))
                .build();
    }
}