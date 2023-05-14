package ru.clevertec.mapper.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.Mapper;

import static java.time.LocalDateTime.*;
import static ru.clevertec.entity.News.*;

@Component
public class NewsCreateUpdateMapper implements Mapper<NewsCreateUpdateDto, News> {

    @Override
    public News map(NewsCreateUpdateDto object) {
        return builder()
                .creationTime(now())
                .title(object.title())
                .subject(object.subject())
                .build();
    }

    @Override
    public News map(NewsCreateUpdateDto fromObject, News toObject) {
        copy(fromObject, toObject);
        return Mapper.super.map(fromObject, toObject);
    }

    private void copy(NewsCreateUpdateDto object, News news) {
        news.setTitle(object.title());
        news.setSubject(object.subject());
    }
}