package ru.clevertec.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.util.UtilClass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class CommentReadMapperTest {

    private CommentReadMapper commentReadMapper;

    @BeforeEach
    void prepare() {
        commentReadMapper = new CommentReadMapper();
    }

    @Test
    void map() {
        Comment comment = UtilClass.comment1;

        CommentReadDto commentReadDto = commentReadMapper.map(comment);

        assertAll(
                () -> assertThat(commentReadDto.subject()).isEqualTo(comment.getSubject()),
                () -> assertThat(commentReadDto.username()).isEqualTo(comment.getUser().getUsername()),
                () -> assertThat(commentReadDto.creationTime()).isEqualTo(comment.getCreationTime())
        );
    }
}