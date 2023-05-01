package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.mapper.impl.CommentCreateMapper;
import ru.clevertec.mapper.impl.CommentReadMapper;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.service.impl.CommentService;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentReadMapper commentReadMapper;
    @Mock
    private CommentCreateMapper commentCreateMapper;
    @InjectMocks
    private CommentService commentService;

    @Test
    void checkFindByIdShouldReturn() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}