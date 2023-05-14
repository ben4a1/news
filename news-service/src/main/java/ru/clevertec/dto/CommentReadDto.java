package ru.clevertec.dto;

import java.time.LocalDateTime;

public record CommentReadDto(Long id,
                             LocalDateTime creationTime,
                             String subject,
                             String username) {
}