package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.clevertec.util.EntitiesGenerator.SUBJECT;

/**
 * sql/data.sql : 4 News, 4 Users, 8 Comments
 */
@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    private final CommentController commentController;
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    /**
     * in test database 8 comments
     */
    @Test
    void checkFindAllShouldReturnSameSize() {
        int expected = commentRepository.findAll().size();
        CommentFilter filter = new CommentFilter(1L, "as", "name");

        int actual = commentController.findAll(filter, PageRequest.of(1, 50)).size();

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
        int countToSave = 1;
        int sizeBefore = commentRepository.findAll().size();
        News news = newsRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);
        assertAll("news and user should be not null",
                () -> assertThat(news).isNotNull(),
                () -> assertThat(user).isNotNull()
        );
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", user.getUsername(), news.getId(), user.getId());
        int expectedSize = sizeBefore + countToSave;

        commentController.create(commentCreateUpdateDto);
        int actualSize = commentRepository.findAll().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        News news = newsRepository.findById(1L).orElse(null);
        User user = userRepository.findById(1L).orElse(null);
        Comment comment = commentRepository.findById(1L).orElse(null);
        assertAll("news and user should be not null",
                () -> assertThat(news).isNotNull(),
                () -> assertThat(user).isNotNull(),
                () -> assertThat(comment).isNotNull()
        );
        Long commentId = comment.getId();
        String subjectBeforeUpdate = comment.getSubject();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("super", user.getUsername(), news.getId(), user.getId());

        commentController.update(commentId, commentCreateUpdateDto);
        Optional<Comment> optionalCommentAfterUpdate = commentRepository.findById(commentId);
        Comment commentAfterUpdate = optionalCommentAfterUpdate.orElse(null);
        assertThat(commentAfterUpdate).isNotNull();
        String subjectAfterUpdate = commentAfterUpdate.getSubject();

        assertThat(subjectBeforeUpdate).isNotEqualTo(subjectAfterUpdate);
    }

    @Test
    void checkDeleteShouldReturnDecreasedSize() {
        int countToDelete = 2;
        List<Comment> comments = commentRepository.findAll().stream().limit(countToDelete).toList();
        int sizeBefore = commentRepository.findAll().size();
        int expectedSize = sizeBefore - countToDelete;

        commentController.delete(comments.get(0).getId());
        commentController.delete(comments.get(1).getId());
        int actualSize = commentRepository.findAll().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    private Comment getComment() {
        return Comment.builder().subject(SUBJECT)
                .user(userRepository.findAll().stream().findAny().orElse(null))
                .creationTime(now())
                .news(newsRepository.findAll().stream().findAny().orElse(null))
                .build();
    }
}