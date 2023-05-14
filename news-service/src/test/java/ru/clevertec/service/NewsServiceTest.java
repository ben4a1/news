package ru.clevertec.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LRUCache;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.mapper.impl.NewsCreateUpdateMapper;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.service.impl.NewsService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.clevertec.util.EntitiesGenerator.NEWS_ID;
import static ru.clevertec.util.EntitiesGenerator.NOW;
import static ru.clevertec.util.EntitiesGenerator.SUBJECT;
import static ru.clevertec.util.EntitiesGenerator.TITLE;
import static ru.clevertec.util.EntitiesGenerator.getNews;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;
    @Mock
    private NewsReadMapper newsReadMapper;
    @Mock
    private NewsCreateUpdateMapper newsCreateUpdateMapper;
    @Mock
    private Cache<Long, News> cache;
    @Mock
    private CacheFactory<Long, News> cacheFactory;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private NewsService newsService;

    @Test
    void checkSave() {
        LRUCache<Long, News> lruCache = new LRUCache<>(5);
        News news = getNews();
        NewsCreateUpdateDto newsCreateUpdateDto = getCreateUpdateDto();
        NewsReadDto expectedReadDto = getReadDto();
        doReturn(news)
                .when(newsRepository).save(news);
        doReturn(news)
                .when(newsCreateUpdateMapper).map(newsCreateUpdateDto);
        doReturn(expectedReadDto)
                .when(newsReadMapper).map(news);
        doReturn(lruCache)
                .when(cacheFactory).createCache();
        doNothing()
                .when(cache).put(news.getId(), news);

        NewsReadDto actualReadDto = newsService.save(newsCreateUpdateDto);

        assertThat(actualReadDto).isEqualTo(expectedReadDto);
        verify(newsCreateUpdateMapper).map(newsCreateUpdateDto);
        verify(newsRepository).save(news);
        verify(newsReadMapper).map(news);
    }

    @Test
    void checkUpdate() {
        News news = getNews();
        NewsCreateUpdateDto createUpdateDto = getCreateUpdateDto();
        NewsReadDto readDto = getReadDto();
        doReturn(Optional.of(news))
                .when(newsRepository).findById(NEWS_ID);
        doReturn(news)
                .when(newsCreateUpdateMapper).map(createUpdateDto, news);
        doReturn(news)
                .when(newsRepository).saveAndFlush(news);
        doReturn(readDto)
                .when(newsReadMapper).map(news);
        doNothing()
                .when(cache).put(news.getId(), news);

        newsService.update(NEWS_ID, createUpdateDto);

        verify(newsRepository).findById(NEWS_ID);
        verify(newsRepository).saveAndFlush(news);
        verify(newsCreateUpdateMapper).map(createUpdateDto, news);
        verify(newsReadMapper).map(news);
    }

    @Test
    void checkFindByIdShouldReturnEquals() {
        News news = getNews();
        doReturn(Optional.of(news))
                .when(newsRepository).findById(NEWS_ID);
        doReturn(getReadDto())
                .when(newsReadMapper).map(news);
        doReturn(false)
                .when(cache.contains(news.getId()));
        doNothing()
                .when(cache).put(news.getId(), news);
        NewsReadDto expectedResult = getReadDto();

        Optional<NewsReadDto> actual = newsService.findById(NEWS_ID);

        actual.ifPresent(actualResult ->
                assertThat(actualResult).isEqualTo(expectedResult));
        verify(newsRepository).findById(NEWS_ID);
        verify(newsReadMapper).map(news);
    }

    @Test
    void checkFindAll() {
        List<News> newsList = Arrays.asList(getNews(), getNews(), getNews());
        doReturn(newsList)
                .when(newsRepository).findAll();
        doReturn(getReadDto())
                .when(newsReadMapper).map(any());

        List<NewsReadDto> actualResultList = newsService.findAll();

        assertThat(actualResultList).hasSize(newsList.size());
        verify(newsReadMapper, times(3)).map(any());
        verify(newsRepository).findAll();
    }


    @Test
    void checkDelete() {
        Long newsId = 1L;
        News news = getNews();
        doReturn(Optional.of(news))
                .when(newsRepository).findById(newsId);
        doNothing()
                .when(cache).remove(newsId);

        newsService.delete(newsId);

        verify(newsRepository).delete(news);
    }

    private NewsCreateUpdateDto getCreateUpdateDto() {
        return new NewsCreateUpdateDto(TITLE, SUBJECT);
    }

    private NewsReadDto getReadDto() {
        return new NewsReadDto(NEWS_ID, NOW, TITLE, SUBJECT, null);
    }
}