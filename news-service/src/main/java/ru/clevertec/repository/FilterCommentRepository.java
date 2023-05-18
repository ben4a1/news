package ru.clevertec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.entity.Comment;

/**
 * Repository for criteria (filter) Comment search
 */
public interface FilterCommentRepository {

    /**
     * @param filter contains news id, part of subject, username
     * @param pageable Basic Java Bean implementation of Pageable.
     * @return {@link org.springframework.data.domain.Page} of Comments
     */
    Page<Comment> findAll(CommentFilter filter, Pageable pageable);
}
