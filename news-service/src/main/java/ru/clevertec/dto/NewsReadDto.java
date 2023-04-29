package ru.clevertec.dto;

import ru.clevertec.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record NewsReadDto(LocalDateTime creationTime,
                          String title,
                          String subject,
                          List<Comment> comments) {
}
