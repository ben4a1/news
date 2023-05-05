package ru.clevertec.mapper.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.Mapper;

@Component
public class NewsReadMapper implements Mapper<News, NewsReadDto> {

    @Override
    public NewsReadDto map(News object) {
        return new NewsReadDto(object.getId(),
                object.getCreationTime(),
                object.getTitle(),
                object.getSubject(),
                object.getComments());
    }
}
