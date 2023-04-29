package ru.clevertec.service;

import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<CommentReadDto> findById(Long id);
    List<CommentReadDto> findAll();
    boolean delete(Long id);
}
