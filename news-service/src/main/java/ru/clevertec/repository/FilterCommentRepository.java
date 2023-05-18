package ru.clevertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.entity.Comment;

public interface FilterCommentRepository {

    Page<Comment> findAll(CommentFilter filter, Pageable pageable);
}