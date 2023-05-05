package ru.clevertec.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.util.UtilClass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NewsReadMapperTest {

    private NewsReadMapper newsReadMapper;

    @BeforeEach
    void prepare() {
        newsReadMapper = new NewsReadMapper();
    }

    @Test
    void map() {
        News news = UtilClass.news2;

        NewsReadDto newsReadDto = newsReadMapper.map(news);

        assertAll(
                () -> assertThat(newsReadDto.id()).isEqualTo(news.getId()),
                () -> assertThat(newsReadDto.creationTime()).isEqualTo(news.getCreationTime()),
                () -> assertThat(newsReadDto.title()).isEqualTo(news.getTitle()),
                () -> assertThat(newsReadDto.subject()).isEqualTo(news.getSubject()),
                () -> assertThat(newsReadDto.comments()).isEqualTo(news.getComments())
        );

    }
}