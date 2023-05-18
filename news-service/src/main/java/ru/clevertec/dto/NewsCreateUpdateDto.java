package ru.clevertec.dto;

/**
 * DTO object for create/update News entity
 *
 * @param title   of the news
 * @param subject content of the news
 */
public record NewsCreateUpdateDto(String title,
                                  String subject) {

}
