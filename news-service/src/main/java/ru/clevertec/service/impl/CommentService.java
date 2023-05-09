package ru.clevertec.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.factory.CacheFactory;
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
    private final Cache<Long, Comment> cache;

    @Transactional
    public CommentReadDto save(CommentCreateUpdateDto comment) {
        Comment commentToSave = commentCreateUpdateMapper.map(comment);
        Comment savedComment = commentRepository.save(commentToSave);
        cache.put(savedComment.getId(), savedComment);
        return commentReadMapper.map(savedComment);
    }

    @Transactional
    public Optional<CommentReadDto> update(Long id, CommentCreateUpdateDto comment) {
        Optional<Comment> commentToUpdate = commentRepository.findById(id);
        if (commentToUpdate.isPresent()) {
            Comment mappedComment = commentCreateUpdateMapper.map(comment, commentToUpdate.get());
            Comment updatedComment = commentRepository.saveAndFlush(mappedComment);
            CommentReadDto commentReadDto = commentReadMapper.map(updatedComment);
            cache.put(id, updatedComment);
            return Optional.of(commentReadDto);
        } else {
            return Optional.empty();
        }
    }

    public Optional<CommentReadDto> findById(Long id) {
        Comment comment;
        if (cache.contains(id)) {
            comment = cache.get(id);
        } else {
            Optional<Comment> commentOptional = commentRepository.findById(id);
            comment = commentOptional.orElse(null);
            cache.put(comment.getId(), comment);
        }
        CommentReadDto commentReadDto = commentReadMapper.map(comment);
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

    public Page<CommentReadDto> findAll(CommentFilter filter, Pageable pageable) {
        return commentRepository.findAll(filter, pageable)
                .map(commentReadMapper::map);
    }
}
