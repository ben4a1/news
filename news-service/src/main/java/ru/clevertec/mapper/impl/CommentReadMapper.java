package ru.clevertec.mapper.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.Mapper;

@Component
public class CommentReadMapper implements Mapper<Comment, CommentReadDto> {

    @Override
    public CommentReadDto map(Comment object) {
        return new CommentReadDto(object.getId(),
                object.getCreationTime(),
                object.getSubject(),
                object.getUser().getUsername());
    }
}