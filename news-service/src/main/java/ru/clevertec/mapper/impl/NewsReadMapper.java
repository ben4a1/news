package ru.clevertec.mapper.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.Mapper;

/**
 * Implementation for news display mapping
 */
@Component
public class NewsReadMapper implements Mapper<News, NewsReadDto> {

    @Override
    public NewsReadDto map(News object) {
        return new NewsReadDto(object.getId(),
                object.getCreationTime(),
                object.getTitle(),
                object.getSubject(),
                object.getComments()
                        .stream()
                        .map(Comment::getSubject)
                        .toList());
    }
}
