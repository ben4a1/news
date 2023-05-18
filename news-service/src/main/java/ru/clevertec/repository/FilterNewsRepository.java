package ru.clevertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;

/**
 * Repository for criteria (filter) News search
 */
public interface FilterNewsRepository {

    /**
     * @param filter contains  part of subject, title
     * @param pageable Basic Java Bean implementation of Pageable.
     * @return {@link org.springframework.data.domain.Page} of Comments
     */
    Page<News> findAll(NewsFilter filter, Pageable pageable);
}
