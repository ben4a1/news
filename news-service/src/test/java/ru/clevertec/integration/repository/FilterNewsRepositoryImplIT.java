package ru.clevertec.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.repository.impl.FilterNewsRepositoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class FilterNewsRepositoryImplIT extends IntegrationTestBase {

    private final FilterNewsRepositoryImpl repository;

    @Test
    void findAll() {
        int expected = 2;
        NewsFilter filter = new NewsFilter("tit", "sub");

        Page<News> page = repository.findAll(filter, PageRequest.of(1, 2));
        int actual = page.getSize();

        assertThat(actual).isEqualTo(expected);
    }
}