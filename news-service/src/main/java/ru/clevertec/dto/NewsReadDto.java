package ru.clevertec.dto;

import ru.clevertec.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record NewsReadDto(Long id,
                          LocalDateTime creationTime,
                          String title,
                          String subject,
                          List<String> comments) {
}