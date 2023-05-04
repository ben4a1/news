package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.NewsController;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.util.UtilClass.listOf4News;
import static ru.clevertec.util.UtilClass.news1;
import static ru.clevertec.util.UtilClass.news2;
import static ru.clevertec.util.UtilClass.news3;

@RequiredArgsConstructor
class NewsControllerIT extends IntegrationTestBase {

    private final NewsController newsController;
    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;

    @Test
    void checkFindAllShouldReturnSameSize() {
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        int expected = newsRepository.findAll().size();

        int actual = newsController.findAll().size();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findById() {
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        Optional<News> optionalNews = newsRepository.findById(1L);
        News news = optionalNews.orElse(null);
        assertThat(news).isNotNull();
        NewsReadDto expected = newsReadMapper.map(news);

        NewsReadDto actual = newsController.findById(1L);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkCreateShouldReturnSameTitle() {
        NewsCreateUpdateDto newsDto = new NewsCreateUpdateDto("NEWS", "BIG NEWS");

        NewsReadDto savedNews = newsController.create(newsDto);
        String titleFromController = savedNews.title();
        Long id = savedNews.id();
        Optional<News> newsFromRepo = newsRepository.findById(id);
        News news = newsFromRepo.orElse(null);
        assertThat(news).isNotNull();
        String titleFromRepo = news.getTitle();

        assertThat(titleFromController).isEqualTo(titleFromRepo);
    }

    @Test
    void update() {
        newsRepository.save(news1);
        newsRepository.save(news2);
        Optional<News> optionalBeforeUpdate = newsRepository.findById(2L);
        News newsBeforeUpdate = optionalBeforeUpdate.orElse(null);
        assertThat(newsBeforeUpdate).isNotNull();
        String titleBeforeUpdate = newsBeforeUpdate.getTitle();
        NewsCreateUpdateDto newsCreateUpdateDto = new NewsCreateUpdateDto("another title", "same");

        newsController.update(2L, newsCreateUpdateDto);
        Optional<News> optionalAfterUpdate = newsRepository.findById(2L);
        News newsAfterUpdate = optionalAfterUpdate.orElse(null);
        assertThat(newsAfterUpdate).isNotNull();
        String titleAfterUpdate = newsBeforeUpdate.getTitle();

        assertThat(titleAfterUpdate).isNotEqualTo(titleBeforeUpdate);
    }

    @Test
    void delete() {
        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        int sizeBefore = newsRepository.findAll().size();

        newsController.delete(1L);
        newsController.delete(2L);
        int sizeAfter = newsRepository.findAll().size();

        assertThat(sizeAfter).isLessThan(sizeBefore);
    }
}