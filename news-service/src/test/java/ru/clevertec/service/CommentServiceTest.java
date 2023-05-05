package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.impl.CommentCreateUpdateMapper;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.service.impl.CommentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.clevertec.util.EntitiesGenerator.COMMENT_ID;
import static ru.clevertec.util.EntitiesGenerator.CREATION_TIME;
import static ru.clevertec.util.EntitiesGenerator.NEWS_ID;
import static ru.clevertec.util.EntitiesGenerator.SUBJECT;
import static ru.clevertec.util.EntitiesGenerator.USERNAME;
import static ru.clevertec.util.EntitiesGenerator.USER_ID;
import static ru.clevertec.util.EntitiesGenerator.getComment;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

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
        Comment comment = getComment();
        CommentCreateUpdateDto commentCreateUpdateDto = new CommentCreateUpdateDto(SUBJECT, USERNAME, NEWS_ID, USER_ID);
        CommentReadDto expectedReadDto = getCommentReadDto(USERNAME);
        doReturn(comment)
                .when(commentCreateUpdateMapper).map(commentCreateUpdateDto);
        doReturn(comment)
                .when(commentRepository).save(comment);
        doReturn(expectedReadDto)
                .when(commentReadMapper).map(comment);

        CommentReadDto actualReadDto = commentService.save(commentCreateUpdateDto);

        assertThat(actualReadDto).isEqualTo(expectedReadDto);
        verify(commentCreateUpdateMapper).map(commentCreateUpdateDto);
        verify(commentRepository).save(comment);
        verify(commentReadMapper).map(comment);
    }

    @Test
    void checkUpdate() {
        String username = "alibaba";
        CommentCreateUpdateDto createUpdateDto = getCommentCreateUpdateDto(username);
        CommentReadDto commentReadDto = getCommentReadDto(username);
        Comment comment = getComment();
        doReturn(Optional.of(comment))
                .when(commentRepository).findById(COMMENT_ID);
        doReturn(comment)
                .when(commentCreateUpdateMapper).map(createUpdateDto, comment);
        doReturn(comment)
                .when(commentRepository).saveAndFlush(comment);
        doReturn(commentReadDto)
                .when(commentReadMapper).map(comment);

        commentService.update(COMMENT_ID, createUpdateDto);

        verify(commentRepository).findById(COMMENT_ID);
        verify(commentRepository).saveAndFlush(comment);
        verify(commentCreateUpdateMapper).map(createUpdateDto, comment);
        verify(commentReadMapper).map(Optional.of(comment).get());
    }

    @Test
    void checkFindByIdShouldReturnEquals() {
        Comment comment = getComment();
        doReturn(Optional.of(comment))
                .when(commentRepository).findById(COMMENT_ID);
        doReturn(getCommentReadDto(USERNAME))
                .when(commentReadMapper).map(comment);
        CommentReadDto expectedResult = getCommentReadDto(USERNAME);

        Optional<CommentReadDto> actualResult = commentService.findById(COMMENT_ID);

        actualResult.ifPresent(actual -> assertThat(actual).isEqualTo(expectedResult));
        verify(commentRepository).findById(NEWS_ID);
        verify(commentReadMapper).map(comment);
    }

    @Test
    void checkFindAll() {
        List<Comment> comments = Arrays.asList(getComment(), getComment());
        doReturn(comments)
                .when(commentRepository).findAll();
        doReturn(getCommentReadDto("username"))
                .when(commentReadMapper).map(any());

        List<CommentReadDto> actualResult = commentService.findAll();

        assertThat(actualResult).hasSize(comments.size());
        verify(commentReadMapper, times(2)).map(any());
        verify(commentRepository).findAll();
    }

    @Test
    void checkDelete() {
        Comment comment = getComment();
        doReturn(Optional.of(comment))
                .when(commentRepository).findById(COMMENT_ID);

        commentService.delete(comment.getId());

        verify(commentRepository).delete(comment);
    }

    private CommentCreateUpdateDto getCommentCreateUpdateDto(String username) {
        return new CommentCreateUpdateDto(SUBJECT, username, 1L, 2L);
    }

    private CommentReadDto getCommentReadDto(String username) {
        return new CommentReadDto(1L, CREATION_TIME, SUBJECT, username);
    }
}