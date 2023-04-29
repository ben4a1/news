package ru.clevertec.service;

import ru.clevertec.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    boolean delete(Long id);
}
