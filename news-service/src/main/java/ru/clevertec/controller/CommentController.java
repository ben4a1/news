package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.aop.RestLog;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.dto.CommentCreateUpdateDto;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.service.impl.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @RestLog(uri = "/api/v1/comments")
    @GetMapping
    public PageResponse<CommentReadDto> findAll(CommentFilter filter, Pageable pageable) {
        Page<CommentReadDto> page = commentService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @RestLog(uri = "/api/v1/comments/{id}")
    @GetMapping("/{id}")
    public CommentReadDto findById(@PathVariable("id") Long id) {
        return commentService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RestLog(uri = "/api/v1/comments")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommentReadDto create(@RequestBody CommentCreateUpdateDto comment) {
        return commentService.save(comment);
    }

    @RestLog(uri = "/api/v1/comments/{id}")
    @PutMapping("/{id}")
    public CommentReadDto update(@PathVariable("id") Long id, @RequestBody CommentCreateUpdateDto comment) {
        return commentService.update(id, comment).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RestLog(uri = "/api/v1/comments/{id}")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!commentService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}