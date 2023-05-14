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

@RequiredArgsConstructor
public class CommentRepositoryIT extends IntegrationTestBase {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;

    @Test
    void checkFindAllWithFilter(){
        List<Comment> expectedCommentList = newsRepository.findById(1L).get().getComments();
        CommentFilter filter = new CommentFilter(1L, "ec", "name");

        Page<Comment> all = commentRepository.findAll(filter, PageRequest.of(0, 10));
        List<Comment> actualCommentList = all.getContent();

        Assertions.assertThat(actualCommentList).isEqualTo(expectedCommentList);
    }
}