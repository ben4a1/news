package ru.clevertec.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO object for read News entity
 *
 * @param id of the news
 * @param creationTime time of creation of the news
 * @param title of the news
 * @param subject content of the news
 * @param comments news comment sheet
 */
public record NewsReadDto(Long id,
                          LocalDateTime creationTime,
                          String title,
                          String subject,
                          List<String> comments) {
}
