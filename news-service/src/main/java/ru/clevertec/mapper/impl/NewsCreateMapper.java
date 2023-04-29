package ru.clevertec.mapper.impl;

import ru.clevertec.dto.NewsCreateDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.Mapper;

import static java.time.LocalDateTime.*;
import static ru.clevertec.entity.News.*;

public class NewsCreateMapper implements Mapper<NewsCreateDto, News> {
    @Override
    public News map(NewsCreateDto object) {
        return builder()
                .creationTime(now())
                .title(object.title())
                .subject(object.subject())
                .build();
    }
}
