package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import ru.clevertec.controller.NewsController;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.util.EntitiesGenerator.SUBJECT;
import static ru.clevertec.util.EntitiesGenerator.TITLE;

/**
 * sql/data.sql : 4 News, 4 Users, 8 Comments
 */
@RequiredArgsConstructor
class NewsControllerIT extends IntegrationTestBase {

    private final NewsController newsController;
    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;

    /**
     * 4 News in test DB
     */
    @Test
    void checkFindAllShouldReturnSameSize() {
        int expected = newsRepository.findAll().size();

        //TODO
        int actual = newsController.findAll(null, Pageable.ofSize(2)).size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindByIdShouldReturnEquals() {
        News news = getNews();
        News savedNews = newsRepository.save(news);
        Long savedNewsId = savedNews.getId();
        NewsReadDto expected = newsReadMapper.map(news);

        NewsReadDto actual = newsController.findById(savedNewsId);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkCreateShouldReturnSameTitle() {
        NewsCreateUpdateDto newsDto = new NewsCreateUpdateDto("NEWS", "BIG NEWS");

        NewsReadDto savedNews = newsController.create(newsDto);
        Long newsId = savedNews.id();
        String titleFromController = savedNews.title();
        Optional<News> newsFromRepo = newsRepository.findById(newsId);
        News news = newsFromRepo.orElse(null);
        assertThat(news).isNotNull();
        String titleFromRepo = news.getTitle();

        assertThat(titleFromController).isEqualTo(titleFromRepo);
    }

    @Test
    void checkUpdateShouldReturnNotEquals() {
        Long newsId = 2L;
        News news = newsRepository.findById(newsId).orElse(null);
        String newsTitleBefore = news.getTitle();
        NewsCreateUpdateDto newsCreateUpdateDto = new NewsCreateUpdateDto("another title", "same");

        newsController.update(newsId, newsCreateUpdateDto);
        Optional<News> optionalAfterUpdate = newsRepository.findById(newsId);
        News newsAfterUpdate = optionalAfterUpdate.orElse(null);
        assertThat(newsAfterUpdate).isNotNull();
        String titleAfterUpdate = newsAfterUpdate.getTitle();

        assertThat(titleAfterUpdate).isNotEqualTo(newsTitleBefore);
    }

    @Test
    void checkDeleteShouldReturnDecreasedSize() {
        int countToDelete = 2;
        List<News> news = newsRepository.findAll().stream().limit(countToDelete).toList();
        int sizeBefore = newsRepository.findAll().size();
        int expectedSize = sizeBefore - countToDelete;

        news.forEach(it -> newsController.delete(it.getId()));
        int actualSize = newsRepository.findAll().size();

        assertThat(actualSize).isEqualTo(expectedSize);
    }

    private News getNews() {
        return News.builder()
                .title(TITLE)
                .subject(SUBJECT)
                .creationTime(now())
                .build();
    }
}