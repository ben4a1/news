package ru.clevertec.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.impl.NewsCreateUpdateMapper;
import ru.clevertec.util.UtilClass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class NewsCreateUpdateMapperTest {

    private static final String TITLE = "title";
    private static final String SUBJECT = "subject";
    private NewsCreateUpdateMapper newsCreateUpdateMapper;

    @BeforeEach
    void prepare() {
        newsCreateUpdateMapper = new NewsCreateUpdateMapper();
    }

    @Test
    void map() {
        NewsCreateUpdateDto newsCreateUpdateDto = new NewsCreateUpdateDto(TITLE, SUBJECT);

        News news = newsCreateUpdateMapper.map(newsCreateUpdateDto);

        assertAll(
                () -> assertThat(news.getSubject()).isEqualTo(newsCreateUpdateDto.subject()),
                () -> assertThat(news.getTitle()).isEqualTo(newsCreateUpdateDto.title())
        );
    }

    @Test
    void checkMapWithTwoParams() {
        String anotherSubject = "another";
        String anotherTitle = "turtles";
        NewsCreateUpdateDto newsCreateUpdateDto = new NewsCreateUpdateDto(anotherTitle, anotherSubject);
        News news = UtilClass.news3;
        String subjectBefore = news.getSubject();
        String titleBefore = news.getTitle();

        newsCreateUpdateMapper.map(newsCreateUpdateDto, news);
        String subjectAfter = news.getSubject();
        String titleAfter = news.getTitle();

        assertAll(
                () -> assertThat(subjectAfter).isNotEqualTo(subjectBefore),
                () -> assertThat(titleAfter).isNotEqualTo(titleBefore)
        );
    }
}