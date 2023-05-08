package ru.clevertec.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.entity.News;
import ru.clevertec.entity.News_;
import ru.clevertec.repository.FilterNewsRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterNewsRepositoryImpl implements FilterNewsRepository {

    private final EntityManager entityManager;

    @Override
    public Page<News> findAll(NewsFilter filter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteria = cb.createQuery(News.class);
        Root<News> news = criteria.from(News.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter != null) {
            if (filter.title() != null) {
                predicates.add(cb.like(news.get(News_.SUBJECT), "%" + filter.subject() + "%"));
            }
            if (filter.subject() != null) {
                predicates.add(cb.like(news.get(News_.TITLE), "%" + filter.title() + "%"));
            }
            criteria.select(news).where(
                    predicates.toArray(Predicate[]::new)
            );
        }
        List<News> resultList = entityManager.createQuery(criteria)
                .getResultList();
        long size = resultList.size();
        return PageableExecutionUtils.getPage(resultList, pageable, () -> size);
    }
}
