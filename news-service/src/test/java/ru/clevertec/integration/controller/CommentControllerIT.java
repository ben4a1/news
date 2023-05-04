package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.util.UtilClass.listOf8Comments;
import static ru.clevertec.util.UtilClass.news1;
import static ru.clevertec.util.UtilClass.news2;
import static ru.clevertec.util.UtilClass.news3;
import static ru.clevertec.util.UtilClass.news4;
import static ru.clevertec.util.UtilClass.username1Admin;
import static ru.clevertec.util.UtilClass.username2Journalist;
import static ru.clevertec.util.UtilClass.username3Journalist;
import static ru.clevertec.util.UtilClass.username4Subscriber;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    private final CommentController commentController;
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private List<Comment> comments;

    @BeforeEach
    void prepareDatabase() {
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        newsRepository.save(news4);
        userRepository.save(username1Admin);
        userRepository.save(username2Journalist);
        userRepository.save(username3Journalist);
        userRepository.save(username4Subscriber);
        comments = listOf8Comments();

    }

    @Test
    void checkFindAllShouldReturnSameSize() {
        commentRepository.save(comments.get(1));
        commentRepository.save(comments.get(2));
        commentRepository.save(comments.get(3));
        int expected = commentRepository.findAll().size();

        int actual = commentController.findAll().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnUsername1() {
        Comment comment = comments.stream().filter(com -> "username1".equalsIgnoreCase(com.getUser().getUsername())).findAny().orElse(null);
        assertThat(comment).isNotNull();
        commentRepository.save(comment);
        Long commentId = comment.getId();
        String expectedResult = "username1";

        var actualResult = commentController.findById(commentId).username();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        commentRepository.save(comments.get(0));
        commentRepository.save(comments.get(1));
        int sizeBefore = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);

        commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        commentRepository.save(comments.get(0));
        commentRepository.save(comments.get(1));
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
        commentRepository.save(comments.get(0));
        commentRepository.save(comments.get(1));
        commentRepository.save(comments.get(2));
        commentRepository.save(comments.get(3));
        commentRepository.save(comments.get(4));
        commentRepository.save(comments.get(5));
        int sizeBefore = commentRepository.findAll().size();

        commentController.delete(2L);
        commentController.delete(3L);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeAfter).isLessThan(sizeBefore);
    }
}