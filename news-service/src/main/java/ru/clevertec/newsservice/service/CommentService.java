package ru.clevertec.newsservice.service;

import ru.clevertec.newsservice.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    boolean delete(Long id);
}
