package ru.clevertec.newsservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.newsservice.entity.Comment;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
