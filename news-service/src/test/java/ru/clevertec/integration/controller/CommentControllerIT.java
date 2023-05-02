package ru.clevertec.integration.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import ru.clevertec.controller.CommentController;
import ru.clevertec.dto.CommentReadDto;
import ru.clevertec.integration.IntegrationTestBase;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
class CommentControllerIT extends IntegrationTestBase {

    public static final Long COMMENT_ID = 1L;
    public static final LocalDateTime CREATION_TIME = now();
    public static final String SUBJECT = "Wow, what a news!?";
    private static final String USERNAME = "Max";
    private final CommentController commentController;
    @Test
    void findAll() {
    }

    @Test
    void findById() {
        System.out.println();
        CommentReadDto actualResult = commentController.findById(COMMENT_ID);

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}