package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.NewsCreateDto;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.mapper.impl.NewsCreateMapper;
import ru.clevertec.mapper.impl.NewsReadMapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.service.NewsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsReadMapper newsReadMapper;
    private final NewsCreateMapper newsCreateMapper;

    @Override
    public NewsReadDto save(NewsCreateDto news) {
        return Optional.of(news)
                .map(newsCreateMapper::map)
                .map(newsRepository::save)
                .map(newsReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<NewsReadDto> update(Long id, NewsCreateDto news) {
        return newsRepository.findById(id)
                .map(entity ->
                        newsCreateMapper.map(news, entity))
                .map(newsRepository::saveAndFlush)
                .map(newsReadMapper::map);
    }

    @Override
    public Optional<NewsReadDto> findById(Long id) {
        return newsRepository.findById(id)
                .map(newsReadMapper::map);
    }

    @Override
    public List<NewsReadDto> findAll() {
        return newsRepository.findAll()
                .stream()
                .map(newsReadMapper::map)
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        return newsRepository.findById(id)
                .map(entity -> {
                    newsRepository.delete(entity);
                    newsRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
