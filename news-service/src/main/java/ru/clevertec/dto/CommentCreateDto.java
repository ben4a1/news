package ru.clevertec.dto;

public record CommentCreateDto(String subject,
                               String username,
                               Long newsId) {
}
