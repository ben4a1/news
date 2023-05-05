package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.impl.NewsCreateUpdateMapper;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.service.impl.NewsService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.clevertec.util.UtilClass.news1;
import static ru.clevertec.util.UtilClass.news2;
import static ru.clevertec.util.UtilClass.news3;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {

    private static final Long NEWS_ID = 1L;
    private static final String TITLE = "title";
    private static final String SUBJECT = "subject";
    private static final LocalDateTime NOW = now();
    public static final LocalDateTime CREATION_TIME = LocalDateTime.of(2022, 10, 9, 12, 12);
    @Mock
    private NewsRepository newsRepository;
    @Mock
    private NewsReadMapper newsReadMapper;
    @Mock
    private NewsCreateUpdateMapper newsCreateUpdateMapper;
    @InjectMocks
    private NewsService newsService;

    @Test
    void checkSave() {
        News news = News.builder().id(NEWS_ID).creationTime(NOW)
                .title(TITLE).subject(SUBJECT).build();
        NewsCreateUpdateDto newsCreateUpdateDto = getCreateUpdateDto();
        NewsReadDto expectedReadDto = getReadDto();
        doReturn(news)
                .when(newsRepository).save(news);
        doReturn(news)
                .when(newsCreateUpdateMapper).map(newsCreateUpdateDto);
        doReturn(expectedReadDto)
                .when(newsReadMapper).map(news);

        NewsReadDto actualReadDto = newsService.save(newsCreateUpdateDto);

        assertThat(actualReadDto).isEqualTo(expectedReadDto);
        verify(newsCreateUpdateMapper).map(newsCreateUpdateDto);
        verify(newsRepository).save(news);
        verify(newsReadMapper).map(news);
    }

    @Test
    void checkUpdate() {
        NewsCreateUpdateDto createUpdateDto = getCreateUpdateDto();
        NewsReadDto readDto = getReadDto();
        doReturn(Optional.of(news1))
                .when(newsRepository).findById(NEWS_ID);
        doReturn(news1)
                .when(newsCreateUpdateMapper).map(createUpdateDto, news1);
        when(newsRepository.saveAndFlush(news1))
                .thenReturn(news1);
        doReturn(readDto)
                .when(newsReadMapper).map(news1);

        newsService.update(NEWS_ID, createUpdateDto);

        verify(newsRepository).findById(NEWS_ID);
        verify(newsRepository).saveAndFlush(news1);
        verify(newsCreateUpdateMapper).map(createUpdateDto, news1);
        verify(newsReadMapper).map(news1);
    }

    @Test
    void checkFindByIdShouldReturnEquals() {
        News news = getNews();
        doReturn(Optional.of(news))
                .when(newsRepository).findById(NEWS_ID);
        doReturn(getReadDto())
                .when(newsReadMapper).map(news);
        NewsReadDto expectedResult = getReadDto();

        Optional<NewsReadDto> actual = newsService.findById(NEWS_ID);

        actual.ifPresent(actualResult ->
                assertThat(actualResult).isEqualTo(expectedResult));
        verify(newsRepository).findById(NEWS_ID);
        verify(newsReadMapper).map(news);
    }

    @Test
    void checkFindAll() {
        List<News> newsList = Arrays.asList(news1, news2, news3);
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
        doReturn(Optional.of(news1))
                .when(newsRepository).findById(NEWS_ID);

        newsService.delete(NEWS_ID);

        verify(newsRepository).delete(news1);
    }

    private News getNews() {
        return News.builder()
                .id(NEWS_ID)
                .creationTime(CREATION_TIME)
                .title(TITLE)
                .subject(SUBJECT)
                .build();
    }

    private NewsCreateUpdateDto getCreateUpdateDto() {
        return new NewsCreateUpdateDto(TITLE, SUBJECT);
    }

    private NewsReadDto getReadDto() {
        return new NewsReadDto(NEWS_ID, NOW, TITLE, SUBJECT, null);
    }
}