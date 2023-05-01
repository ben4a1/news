package ru.clevertec.mapper.impl;

import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.Mapper;

import static java.time.LocalDateTime.*;
import static ru.clevertec.entity.News.*;

public class NewsCreateMapper implements Mapper<NewsCreateUpdateDto, News> {

    @Override
    public News map(NewsCreateUpdateDto object) {
        return builder()
                .creationTime(now())
                .title(object.title())
                .subject(object.subject())
                .build();
    }
}
