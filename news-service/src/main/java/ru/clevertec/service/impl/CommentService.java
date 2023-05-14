package ru.clevertec.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.cache.Cache;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.mapper.impl.CommentCreateUpdateMapper;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentReadMapper commentReadMapper;
    private final CommentCreateUpdateMapper commentCreateUpdateMapper;
    private final Cache<Long, Comment> cache;
    private final EntityManager entityManager;

    public CommentService(CommentRepository commentRepository, CommentReadMapper commentReadMapper, CommentCreateUpdateMapper commentCreateUpdateMapper, CacheFactory<Long, Comment> cacheFactory, EntityManager entityManager) {
        this.commentRepository = commentRepository;
        this.commentReadMapper = commentReadMapper;
        this.commentCreateUpdateMapper = commentCreateUpdateMapper;
        this.cache = cacheFactory.createCache();
        this.entityManager = entityManager;
    }

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
            entityManager.refresh(comment);
        } else {
            Optional<Comment> commentOptional = commentRepository.findById(id);
            comment = commentOptional.orElse(null);
            if (comment != null) {
                cache.put(comment.getId(), comment);
            }
        }
        return Optional.ofNullable(comment)
                .map(commentReadMapper::map);
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

    public List<CommentReadDto> findAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentReadMapper::map)
                .toList();
    }

    public Page<CommentReadDto> findAll(CommentFilter filter, Pageable pageable) {
        if (filter == null){
            return commentRepository.findAll(pageable)
                    .map(commentReadMapper::map);
        }
        return commentRepository.findAll(filter, pageable)
                .map(commentReadMapper::map);
    }
}
