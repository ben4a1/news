package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.mapper.impl.CommentCreateUpdateMapper;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentReadMapper commentReadMapper;
    private final CommentCreateUpdateMapper commentCreateUpdateMapper;

    @Transactional
    public CommentReadDto save(CommentCreateUpdateDto comment) {
        return Optional.of(comment)
                .map(commentCreateUpdateMapper::map)
                .map(commentRepository::save)
                .map(commentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CommentReadDto> update(Long id, CommentCreateUpdateDto comment) {
        return commentRepository.findById(id)
                .map(entity ->
                        commentCreateUpdateMapper.map(comment, entity))
                .map(commentRepository::saveAndFlush)
                .map(commentReadMapper::map);
    }

    public Optional<CommentReadDto> findById(Long id) {
        return commentRepository.findById(id)
                .map(commentReadMapper::map);
    }

    public List<CommentReadDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentReadMapper::map)
                .toList();
    }

    @Transactional
    public boolean delete(Long id) {
        return commentRepository.findById(id)
                .map(entity -> {
                    commentRepository.delete(entity);
                    commentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
