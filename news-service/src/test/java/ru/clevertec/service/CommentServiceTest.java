package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.User;
import ru.clevertec.mapper.impl.CommentCreateUpdateMapper;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.service.impl.CommentService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static ru.clevertec.util.UtilClass.comment1;
import static ru.clevertec.util.UtilClass.comment2;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    public static final Long COMMENT_ID = 1L;
    private static final Long NEWS_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final String USERNAME = "Max";
    public static final String SUBJECT = "Pugacheva reacted to the death of rabbit";
    public static final LocalDateTime CREATION_TIME = now();
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentReadMapper commentReadMapper;
    @Mock
    private CommentCreateUpdateMapper commentCreateUpdateMapper;
    @InjectMocks
    private CommentService commentService;

    @Test
    void checkSave() {
        Comment comment = Comment.builder().id(NEWS_ID).subject(SUBJECT).build();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto(SUBJECT, USERNAME, NEWS_ID, USER_ID);
        CommentReadDto expectedReadDto = new CommentReadDto(COMMENT_ID, CREATION_TIME, SUBJECT, USERNAME);
        doReturn(comment)
                .when(commentCreateUpdateMapper).map(commentCreateUpdateDto);
        doReturn(comment)
                .when(commentRepository).save(comment);
        doReturn(expectedReadDto)
                .when(commentReadMapper).map(comment);

        CommentReadDto actualReadDto = commentService.save(commentCreateUpdateDto);

        assertThat(actualReadDto).isEqualTo(expectedReadDto);
    }

    @Test
    void checkSaveShouldReturnExists() {
        CommentCreateUpdateDto commentCreateUpdateDto = getCommentCreateUpdateDto("username1");
        CommentReadDto commentReadDto = getCommentReadDto("username1");
        doReturn(comment1)
                .when(commentRepository).save(comment1);
        doReturn(comment1)
                .when(commentCreateUpdateMapper).map(commentCreateUpdateDto);
        doReturn(commentReadDto)
                .when(commentReadMapper).map(comment1);

        CommentReadDto actualResult = commentService.save(commentCreateUpdateDto);

        assertThat(actualResult).isEqualTo(commentReadDto);
    }

    @Test
    void update() {
        CommentCreateUpdateDto createUpdateDto = getCommentCreateUpdateDto("alibaba");
        doReturn(Optional.of(comment1))
                .when(commentRepository).findById(COMMENT_ID);
        doReturn(comment1)
                .when(commentCreateUpdateMapper).map(createUpdateDto, comment1);

        commentService.update(COMMENT_ID, createUpdateDto);

        verify(commentRepository).saveAndFlush(comment1);
    }

    @Test
    void checkFindByIdShouldReturnEquals() {
        Comment comment = Comment.builder()
                .id(COMMENT_ID)
                .creationTime(CREATION_TIME)
                .subject(SUBJECT)
                .user(User.builder().username(USERNAME).build()).build();
        doReturn(Optional.of(comment))
                .when(commentRepository).findById(COMMENT_ID);
        doReturn(new CommentReadDto(COMMENT_ID, CREATION_TIME, SUBJECT, USERNAME))
                .when(commentReadMapper).map(comment);
        CommentReadDto expectedResult = new CommentReadDto(COMMENT_ID, CREATION_TIME, SUBJECT, USERNAME);

        Optional<CommentReadDto> actualResult = commentService.findById(COMMENT_ID);
        assertThat(actualResult).isPresent();

        actualResult.ifPresent(actual -> assertThat(actual).isEqualTo(expectedResult));
    }

    @Test
    void findAll() {
        List<Comment> comments = Arrays.asList(comment1, comment2);
        doReturn(comments)
                .when(commentRepository).findAll();
        doReturn(getCommentReadDto("username"))
                .when(commentReadMapper).map(any());

        List<CommentReadDto> actualResult = commentService.findAll();

        assertThat(actualResult).hasSize(comments.size());
    }

    @Test
    void delete() {
        Comment comment = comment1;
        doReturn(Optional.of(comment1))
                .when(commentRepository).findById(COMMENT_ID);

        commentService.delete(comment.getId());

        verify(commentRepository).delete(comment);
    }

    private CommentCreateUpdateDto getCommentCreateUpdateDto(String username) {
        return new CommentCreateUpdateDto(SUBJECT, username, 1L, 2L);
    }

    private CommentReadDto getCommentReadDto(String username) {
        return new CommentReadDto(1L, now(), SUBJECT, username);
    }
}