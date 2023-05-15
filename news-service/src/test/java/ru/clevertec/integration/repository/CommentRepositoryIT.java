package ru.clevertec.integration.repository;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.entity.Comment;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.repository.CommentRepository;
import ru.clevertec.repository.NewsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class CommentRepositoryIT extends IntegrationTestBase {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;

    @Test
    void checkFindAllWithFilter() {
        long commentId = 1L;
        int pageNumber = 0;
        int pageSize = 10;
        List<Comment> expectedCommentList = newsRepository.findById(commentId).get().getComments();
        CommentFilter filter = new CommentFilter(commentId, "ec", "name");

        Page<Comment> all = commentRepository.findAll(filter, PageRequest.of(pageNumber, pageSize));
        List<Comment> actualCommentList = all.getContent();

        assertThat(actualCommentList).isEqualTo(expectedCommentList);
    }
}