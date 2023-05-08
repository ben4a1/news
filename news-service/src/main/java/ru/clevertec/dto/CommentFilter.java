package ru.clevertec.dto;

public record CommentFilter(Long newsId,
                            String subject,
                            String username) {
}