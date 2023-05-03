package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.integration.util.UtilClass.comment1;
import static ru.clevertec.integration.util.UtilClass.comment2;
import static ru.clevertec.integration.util.UtilClass.comment3;
import static ru.clevertec.integration.util.UtilClass.comment4;
import static ru.clevertec.integration.util.UtilClass.comment5;
import static ru.clevertec.integration.util.UtilClass.comment6;
import static ru.clevertec.integration.util.UtilClass.comment7;
import static ru.clevertec.integration.util.UtilClass.comment8;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    public static final Long COMMENT_ID = 1L;
    private final CommentController commentController;
    private final CommentRepository commentRepository;
    private final CommentReadMapper commentReadMapper;

    @Test
    void checkFindAllShouldReturnSameSize() {
        int expected = commentRepository.findAll().size();
        int actual = commentController.findAll().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnUsername1() {
        String expectedResult = "username1";

        var actualResult = commentController.findById(COMMENT_ID).username();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        int sizeBefore = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);

        commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeBefore).isLessThan(sizeAfter);
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

        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

    private static List<Comment> listOf8Comments() {
        return new ArrayList<>(List.of(comment1, comment2, comment3, comment4, comment5, comment6, comment7, comment8));
    }
}