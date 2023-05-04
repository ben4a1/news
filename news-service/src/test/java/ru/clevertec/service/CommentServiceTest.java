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
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class cqCommentServiceTest {

    public static final Long COMMENT_ID = 1L;
    public static final LocalDateTime CREATION_TIME = now();
    public static final String SUBJECT = "Pugacheva reacted to the death of rabbit";
    private static final String USERNAME = "Max";
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentReadMapper commentReadMapper;
    @Mock
    private CommentCreateUpdateMapper commentCreateUpdateMapper;
    @InjectMocks
    private CommentService commentService;

    @Test
    void checkFindByIdShouldReturnEquals() {
        Comment comment = getComment();

        doReturn(Optional.of(comment))
                .when(commentRepository).findById(COMMENT_ID);

        doReturn(new CommentReadDto(COMMENT_ID, CREATION_TIME, SUBJECT, USERNAME))
                .when(commentReadMapper).map(comment);

        Optional<CommentReadDto> actualResult = commentService.findById(COMMENT_ID);
        assertThat(actualResult).isPresent();

        CommentReadDto expectedResult = new CommentReadDto(COMMENT_ID, CREATION_TIME, SUBJECT, USERNAME);
        actualResult.ifPresent(actual -> assertThat(actual).isEqualTo(expectedResult));
    }


//    @Test
//    void save() {
//        CommentCreateUpdateDto commentUpdateToSave = new CommentCreateUpdateDto("s", "user", 1L, 2L);
//        CommentReadDto commentReadDtoForMock = new CommentReadDto(1L, null, "s", "user");
//        doReturn(commentReadDtoForMock).when(com)
//        CommentReadDto commentReadDto = commentService.save(commentUpdateToSave);
//    }
    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    private static Comment getComment() {
        return Comment.builder()
                .id(COMMENT_ID)
                .creationTime(CREATION_TIME)
                .subject(SUBJECT)
                .user(User.builder().username(USERNAME).build())
                .build();
    }
}