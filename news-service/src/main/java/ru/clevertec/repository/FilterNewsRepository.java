package ru.clevertec.repository;

import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;

import java.util.List;

public interface FilterNewsRepository {

    List<News> findAll(NewsFilter filter, Pageable pageable);
}
