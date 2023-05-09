package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.mapper.impl.NewsCreateUpdateMapper;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;
    private final NewsCreateUpdateMapper newsCreateUpdateMapper;
    private final Cache<Long, News> cache;

    @Transactional
    public NewsReadDto save(NewsCreateUpdateDto news) {
        News newsToSave = newsCreateUpdateMapper.map(news);
        News savedNews = newsRepository.save(newsToSave);
        cache.put(savedNews.getId(), savedNews);
        NewsReadDto newsReadDto = newsReadMapper.map(savedNews);
        return newsReadDto;
    }

    @Transactional
    public Optional<NewsReadDto> update(Long id, NewsCreateUpdateDto news) {
        Optional<News> newsToUpdate = newsRepository.findById(id);
        if (newsToUpdate.isPresent()) {
            News mappedNews = newsCreateUpdateMapper.map(news, newsToUpdate.get());
            News updatedNews = newsRepository.saveAndFlush(mappedNews);
            NewsReadDto newsReadDto = newsReadMapper.map(updatedNews);
            return Optional.of(newsReadDto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<NewsReadDto> findById(Long id) {
        NewsReadDto readDto;
        News news;
        if (cache.contains(id)) {
            news = cache.get(id);
        } else {
            Optional<News> newsOptional = newsRepository.findById(id);
            news = newsOptional.orElse(null);
            cache.put(news.getId(), news);
        }
        readDto = newsReadMapper.map(news);
        return Optional.of(readDto);
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<News> newsToDelete = newsRepository.findById(id);
        if (newsToDelete.isPresent()) {
            newsRepository.delete(newsToDelete.get());
            newsRepository.flush();
            cache.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public List<NewsReadDto> findAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsReadMapper::map)
                .toList();
    }

    public Page<NewsReadDto> findAll(NewsFilter filter, Pageable pageable) {
        return newsRepository.findAll(filter, pageable)
                .map(newsReadMapper::map);
    }
}
