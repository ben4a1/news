package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.dto.NewsReadDto;
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

    @Transactional
    public NewsReadDto save(NewsCreateUpdateDto news) {
        return Optional.of(news)
                .map(newsCreateUpdateMapper::map)
                .map(newsRepository::save)
                .map(newsReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<NewsReadDto> update(Long id, NewsCreateUpdateDto news) {
        return newsRepository.findById(id)
                .map(entity ->
                        newsCreateUpdateMapper.map(news, entity))
                .map(newsRepository::saveAndFlush)
                .map(newsReadMapper::map);
    }

    public Optional<NewsReadDto> findById(Long id) {
        return newsRepository.findById(id)
                .map(newsReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return newsRepository.findById(id)
                .map(entity -> {
                    newsRepository.delete(entity);
                    newsRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public List<NewsReadDto> findAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsReadMapper::map)
                .toList();
    }

    public List<NewsReadDto> findAll(NewsFilter filter, Pageable pageable) {
        return newsRepository.findAll(filter, pageable)
                .stream()
                .map(newsReadMapper::map)
                .toList();
    }
}
