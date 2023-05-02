package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.controller.NewsController;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class NewsControllerIT extends IntegrationTestBase {

    private final NewsController newsController;
    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;

    @Test
    void checkFindAllShouldReturnSameSize() {
        int expected = newsRepository.findAll().size();
        int actual = newsController.findAll().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findById() {
        Optional<News> optionalNews = newsRepository.findById(1L);
        News news = optionalNews.orElse(null);
        assert news != null;
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
        assert news != null;
        String titleFromRepo = news.getTitle();
        assertThat(titleFromController).isEqualTo(titleFromRepo);
    }

    @Test
    void update() {
        Optional<News> optionalBeforeUpdate = newsRepository.findById(2L);
        News newsBeforeUpdate = optionalBeforeUpdate.orElse(null);
        assert newsBeforeUpdate != null;
        String titleBeforeUpdate = newsBeforeUpdate.getTitle();
        NewsCreateUpdateDto newsCreateUpdateDto = new NewsCreateUpdateDto("another title", "same");
        newsController.update(2L, newsCreateUpdateDto);
        Optional<News> optionalAfterUpdate = newsRepository.findById(2L);
        News newsAfterUpdate = optionalAfterUpdate.orElse(null);
        assert newsAfterUpdate != null;
        String titleAfterUpdate = newsBeforeUpdate.getTitle();
        assertThat(titleAfterUpdate).isNotEqualTo(titleBeforeUpdate);
    }

    @Test
    void delete() {
        int sizeBefore = newsRepository.findAll().size();
        newsController.delete(1L);
        newsController.delete(2L);
        int sizeAfter = newsRepository.findAll().size();
        assertThat(sizeAfter).isLessThan(sizeBefore);
    }
}