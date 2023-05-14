package ru.clevertec.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.repository.NewsRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class NewsRepositoryIT extends IntegrationTestBase {

    private final NewsRepository repository;

    @Test
    void checkFindAllShouldReturnContainsAll() {
        List<Long> expected = Arrays.asList(1L, 2L);
        NewsFilter filter = new NewsFilter("tit", "sub");

        Page<News> page = repository.findAll(filter, PageRequest.of(0, 10));
        List<Long> actual = page.getContent().stream().map(News::getId).toList();

        assertThat(actual).containsAll(expected);
    }
}