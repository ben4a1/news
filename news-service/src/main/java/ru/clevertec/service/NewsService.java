package ru.clevertec.service;

import ru.clevertec.dto.NewsCreateDto;
import ru.clevertec.dto.NewsReadDto;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    NewsReadDto save(NewsCreateDto comment);
    Optional<NewsReadDto> update(Long id, NewsCreateDto comment);
    Optional<NewsReadDto> findById(Long id);
    List<NewsReadDto> findAll();
    boolean delete(Long id);
}
