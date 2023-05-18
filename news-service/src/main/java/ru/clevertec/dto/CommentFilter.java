package ru.clevertec.dto;

/**
 * ClassFilter for {@link ru.clevertec.controller.CommentController}.findAll()
 *
 * @param newsId id of the news
 * @param subject part of comment
 * @param username of the commenter
 */
public record CommentFilter(Long newsId,
                            String subject,
                            String username) {
}
