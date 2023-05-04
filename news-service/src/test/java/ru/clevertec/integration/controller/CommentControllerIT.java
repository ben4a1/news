package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.entity.User;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.model.Role;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.util.UtilClass.PASSWORD;
import static ru.clevertec.util.UtilClass.SUBJECT;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    private final CommentController commentController;
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Test
    void checkFindAllShouldReturnSameSize() {
        newsRepository.save(getNews("Title1"));
        newsRepository.save(getNews("Hibernate1"));
        userRepository.save(getUser("username1", Role.ADMIN));
        userRepository.save(getUser("username2", Role.JOURNALIST));
        commentRepository.saveAll(Arrays.asList(getComment(), getComment(), getComment()));
        int expected = commentRepository.findAll().size();

        int actual = commentController.findAll().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnUsername1() {
        newsRepository.save(getNews("Title2"));
        newsRepository.save(getNews("Hibernate2"));
        userRepository.save(getUser("username3", Role.ADMIN));
        userRepository.save(getUser("username4", Role.JOURNALIST));
        Comment comment = commentRepository.save(getComment());
        Long commentId = comment.getId();
        String expectedResult = "username3";

        var actualResult = commentController.findById(commentId).username();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void checkCreateShouldReturnIncreasedSize() {
        newsRepository.save(getNews("Title3"));
        newsRepository.save(getNews("Hibernate3"));
        userRepository.save(getUser("username5", Role.ADMIN));
        userRepository.save(getUser("username6", Role.JOURNALIST));
        commentRepository.saveAll(Arrays.asList(getComment(), getComment()));
        int sizeBefore = commentRepository.findAll().size();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto("sub", "username1", 1L, 1L);

        commentController.create(commentCreateUpdateDto);
        int sizeAfter = commentRepository.findAll().size();

        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        newsRepository.save(getNews("Title4"));
        newsRepository.save(getNews("Hibernate4"));
        userRepository.save(getUser("username7", Role.ADMIN));
        userRepository.save(getUser("username8", Role.JOURNALIST));
        commentRepository.saveAll(Arrays.asList(getComment(), getComment()));
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
        newsRepository.save(getNews("Title5"));
        newsRepository.save(getNews("Hibernate5"));
        userRepository.save(getUser("username9", Role.ADMIN));
        userRepository.save(getUser("username10", Role.JOURNALIST));
        commentRepository.saveAll(Arrays.asList(getComment(), getComment(), getComment(), getComment()));
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

    private News getNews(String title) {
        return News.builder().creationTime(now()).title(title).subject(SUBJECT).build();
    }

    private User getUser(String username, Role role) {
        return User.builder().username(username).password(PASSWORD).role(role).build();
    }
}