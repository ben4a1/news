package ru.clevertec.dto;

import java.time.LocalDateTime;

public record CommentFilter(LocalDateTime creationTime,
                            String subject,
                            String username) {
}