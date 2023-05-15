package ru.clevertec.dto;

public record CommentCreateUpdateDto(String subject,
                                     String username,
                                     Long newsId,
                                     Long userId) {
}