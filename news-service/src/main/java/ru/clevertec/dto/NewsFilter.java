package ru.clevertec.dto;

/**
 * ClassFilter for {@link ru.clevertec.controller.NewsController}.findAll()
 *
 * @param title or part of title
 * @param subject part of news`s content
 */
public record NewsFilter(String title,
                         String subject) {
}
