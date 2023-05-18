package ru.clevertec.dto;

/**
 * DTO object for create/update Comment entity
 *
 * @param subject  text of comment
 * @param username of the person who wrote comment
 * @param newsId   id of the news
 * @param userId   id of the user
 */
public record CommentCreateUpdateDto(String subject,
                                     String username,
                                     Long newsId,
                                     Long userId) {

}
