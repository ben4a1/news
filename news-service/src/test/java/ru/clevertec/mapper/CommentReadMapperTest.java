package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.impl.CommentReadMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.clevertec.util.EntitiesGenerator.getComment;


class CommentReadMapperTest {

    private final CommentReadMapper commentReadMapper = new CommentReadMapper();

    @Test
    void checkMap() {
        Comment comment = getComment();

        CommentReadDto commentReadDto = commentReadMapper.map(comment);

        assertAll(
                () -> assertThat(commentReadDto.subject()).isEqualTo(comment.getSubject()),
                () -> assertThat(commentReadDto.username()).isEqualTo(comment.getUser().getUsername()),
                () -> assertThat(commentReadDto.creationTime()).isEqualTo(comment.getCreationTime())
        );
    }
}