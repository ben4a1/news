package ru.clevertec.service.impl;

import jakarta.persistence.EntityManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
@CacheConfig(cacheNames = "newsCache")
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;
    private final NewsCreateUpdateMapper newsCreateUpdateMapper;
    private final Cache<Long, News> cache;
    private final EntityManager entityManager;

    public NewsService(NewsRepository newsRepository, NewsReadMapper newsReadMapper, NewsCreateUpdateMapper newsCreateUpdateMapper, CacheFactory<Long, News> cacheFactory, EntityManager entityManager) {
        this.newsRepository = newsRepository;
        this.newsReadMapper = newsReadMapper;
        this.newsCreateUpdateMapper = newsCreateUpdateMapper;
        this.cache = cacheFactory.createCache();
        this.entityManager = entityManager;
    }

    @CacheEvict(cacheNames = "news", allEntries = true)
    @Transactional
    public NewsReadDto save(NewsCreateUpdateDto news) {
        News newsToSave = newsCreateUpdateMapper.map(news);
        News savedNews = newsRepository.save(newsToSave);
        cache.put(savedNews.getId(), savedNews);
        return newsReadMapper.map(savedNews);
    }

    @CacheEvict(cacheNames = "news", allEntries = true)
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

    @Cacheable(cacheNames = "new", key = "#id", unless = "#result == null")
    public Optional<NewsReadDto> findById(Long id) {
        News news;
        if (cache.contains(id)) {
            news = cache.get(id);
            entityManager.merge(news);
        } else {
            Optional<News> newsOptional = newsRepository.findById(id);
            news = newsOptional.orElse(null);
            if (news != null) {
                cache.put(news.getId(), news);
            }
        }
        return Optional.ofNullable(news)
                .map(newsReadMapper::map);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "new", key = "#id"),
            @CacheEvict(cacheNames = "news", allEntries = true)})
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

    @Cacheable(cacheNames = "news")
    public List<NewsReadDto> findAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsReadMapper::map)
                .toList();
    }

    @Cacheable(cacheNames = "news")
    public Page<NewsReadDto> findAll(NewsFilter filter, Pageable pageable) {
        if (filter == null){
            return newsRepository.findAll(pageable)
                    .map(newsReadMapper::map);
        }
        return newsRepository.findAll(filter, pageable)
                .map(newsReadMapper::map);
    }
}
