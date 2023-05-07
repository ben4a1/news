package ru.clevertec.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;
import ru.clevertec.integration.IntegrationTestBase;
import ru.clevertec.predicate.CriteriaPredicate;
import ru.clevertec.repository.FilterNewsRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
class FilterNewsRepositoryImplIT extends IntegrationTestBase {

    private final FilterNewsRepositoryImpl repository;
        @Test
    void findAll() {
        NewsFilter filter = new NewsFilter("%tit%", "%sub%");
        List<News> news = repository.findAll(filter, null);
        System.out.println(news);
    }
}