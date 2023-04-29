package ru.clevertec.newsservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.newsservice.entity.News;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
}
