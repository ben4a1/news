package ru.clevertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;

public interface FilterNewsRepository {

    Page<News> findAll(NewsFilter filter, Pageable pageable);
}