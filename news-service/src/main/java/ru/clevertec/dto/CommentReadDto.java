package ru.clevertec.dto;

import java.time.LocalDateTime;

public record CommentReadDto(LocalDateTime creationTime,
                             String subject) {
}
