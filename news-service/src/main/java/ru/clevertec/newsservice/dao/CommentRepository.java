package ru.clevertec.newsservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.newsservice.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
