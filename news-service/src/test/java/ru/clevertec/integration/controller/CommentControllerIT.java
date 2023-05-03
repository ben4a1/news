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
        var actualResult = commentController.findById(COMMENT_ID).username();
        Comment expectedComment = listOf8Comments().get(Math.toIntExact(COMMENT_ID - 1));
        var expectedResult = commentReadMapper.map(expectedComment).username();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        int startSize = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);
        commentController.create(commentCreateUpdateDto);
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

    private static List<Comment> listOf8Comments(){
        ArrayList<Comment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        list.add(comment3);
        list.add(comment4);
        list.add(comment5);
        list.add(comment6);
        list.add(comment7);
        list.add(comment8);
        return list;
    }
}