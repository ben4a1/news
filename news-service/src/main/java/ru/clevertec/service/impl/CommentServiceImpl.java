package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.entity.Comment;
import ru.clevertec.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentReadMapper commentReadMapper;
    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Optional<CommentReadDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<CommentReadDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentReadMapper::map)
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
