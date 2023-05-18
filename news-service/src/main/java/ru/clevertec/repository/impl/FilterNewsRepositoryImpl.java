package ru.clevertec.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
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
    public Page<News> findAll(@NonNull NewsFilter filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteria = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteria.from(News.class);
        Predicate[] predicates = getPredicateArray(filter, criteriaBuilder, root);
        criteria.select(root)
                        .where(predicates);
        List<News> resultList = entityManager.createQuery(criteria)
                .setFirstResult(pageable.getPageSize() * (pageable.getPageNumber()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        long total = getNewsCount(criteriaBuilder, filter);
        return PageableExecutionUtils.getPage(resultList, pageable, () -> total);
    }

    /**
     * @param filter {@link ru.clevertec.dto.NewsFilter} contains  part of subject, title
     * @param cb     {@link jakarta.persistence.criteria.CriteriaBuilder}
     * @param root   {@link jakarta.persistence.criteria.Root}
     * @return an array of predicates to add to the query
     */
    private Predicate[] getPredicateArray(NewsFilter filter, CriteriaBuilder cb, Root<News> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.title() != null) {
            predicates.add(cb.like(root.get(News_.TITLE), "%" + filter.title() + "%"));
        }
        if (filter.subject() != null) {
            predicates.add(cb.like(root.get(News_.SUBJECT), "%" + filter.subject() + "%"));
        }
        return predicates.toArray(Predicate[]::new);
    }

    /**
     * @return count of News in query with filter
     */
    private long getNewsCount(CriteriaBuilder criteriaBuilder, NewsFilter filter) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<News> countRoot = countQuery.from(News.class);
        Predicate[] predicates = getPredicateArray(filter, criteriaBuilder, countRoot);
        countQuery.select(criteriaBuilder.count(countRoot))
                .where(predicates);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
