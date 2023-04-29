package ru.clevertec.mapper.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.dto.CommentCreateDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.exception.NewsNotFoundException;
import ru.clevertec.mapper.Mapper;
import ru.clevertec.repository.NewsRepository;
import ru.clevertec.repository.UserRepository;

import static java.time.LocalDateTime.*;
import static ru.clevertec.entity.Comment.*;

@Component
@RequiredArgsConstructor
public class CommentCreateMapper implements Mapper<CommentCreateDto, Comment> {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public Comment map(CommentCreateDto object) {
        return builder()
                .creationTime(now())
                .subject(object.subject())
                .user(userRepository.findByUsername(object.username()))
                .news(newsRepository.findById(object.newsId()).orElse(null))
                .build();
    }
}
