package ru.clevertec.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.mapper.Mapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class CommentCreateMapper implements Mapper<CommentCreateUpdateDto, Comment> {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public Comment map(CommentCreateUpdateDto object) {
        return Comment.builder()
                .creationTime(now())
                .subject(object.subject())
                .user(userRepository.findByUsername(object.username()))
                .news(newsRepository.findById(object.newsId()).orElse(null))
                .build();
    }
}
