package ru.clevertec.dto;

import java.time.LocalDateTime;

/**
 * DTO object for read Comment entity
 *
 * @param id           of the comment
 * @param creationTime time of creation of the comment
 * @param subject      of the comment
 * @param username     id of the user
 */
public record CommentReadDto(Long id,
                             LocalDateTime creationTime,
                             String subject,
                             String username) {

}
