package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.entity.News;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.mapper.impl.NewsCreateUpdateMapper;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;
    private final NewsCreateUpdateMapper newsCreateUpdateMapper;
//    @Qualifier("${cache.algorithm}CacheNewsFactory")
    private final CacheFactory<Long, News> cacheFactory;
    private final Cache<Long, News> cache;

    public NewsService(NewsRepository newsRepository, NewsReadMapper newsReadMapper, NewsCreateUpdateMapper newsCreateUpdateMapper, CacheFactory<Long, News> cacheFactory) {
        this.newsRepository = newsRepository;
        this.newsReadMapper = newsReadMapper;
        this.newsCreateUpdateMapper = newsCreateUpdateMapper;
        this.cacheFactory = cacheFactory;
        this.cache = this.cacheFactory.createCache();
    }

    @Transactional
    public NewsReadDto save(NewsCreateUpdateDto news) {
        News newsToSave = newsCreateUpdateMapper.map(news);
        News savedNews = newsRepository.save(newsToSave);
        cache.put(savedNews.getId(), savedNews);
        return newsReadMapper.map(savedNews);
    }

    @Transactional
    public Optional<NewsReadDto> update(Long id, NewsCreateUpdateDto news) {
        Optional<News> newsToUpdate = newsRepository.findById(id);
        if (newsToUpdate.isPresent()) {
            News mappedNews = newsCreateUpdateMapper.map(news, newsToUpdate.get());
            News updatedNews = newsRepository.saveAndFlush(mappedNews);
            NewsReadDto newsReadDto = newsReadMapper.map(updatedNews);
            cache.put(id, updatedNews);
            return Optional.of(newsReadDto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<NewsReadDto> findById(Long id) {
        News news;
        if (cache.contains(id)) {
            news = cache.get(id);
        } else {
            Optional<News> newsOptional = newsRepository.findById(id);
            news = newsOptional.orElse(null);
            cache.put(news.getId(), news);
        }
        NewsReadDto newsReadDto = newsReadMapper.map(news);
        return Optional.of(newsReadDto);
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
