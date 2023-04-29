package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.service.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public PageResponse<CommentReadDto> findAll()
}
