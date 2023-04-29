package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.database.QPredicates;
import ru.clevertec.dto.CommentCreateDto;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.mapper.impl.CommentCreateMapper;
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
    private final CommentCreateMapper commentCreateMapper;

    @Override
    public CommentReadDto save(CommentCreateDto comment) {
        return Optional.of(comment)
                .map(commentCreateMapper::map)
                .map(commentRepository::save)
                .map(commentReadMapper::map)
                .orElseThrow();
    }

    @Override
    public Optional<CommentReadDto> update(Long id, CommentCreateDto comment) {
        return commentRepository.findById(id)
                .map(entity ->
                        commentCreateMapper.map(comment, entity))
                .map(commentRepository::saveAndFlush)
                .map(commentReadMapper::map);
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
