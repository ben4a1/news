package ru.clevertec.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteria = cb.createQuery(News.class);
        CriteriaQuery<Long> countCriteria = cb.createQuery(Long.class);
        Root<News> root = criteria.from(News.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.title() != null) {
            predicates.add(cb.like(root.get(News_.TITLE), "%" + filter.subject() + "%"));
        }
        if (filter.subject() != null) {
            predicates.add(cb.like(root.get(News_.SUBJECT), "%" + filter.title() + "%"));
        }
        criteria.select(root).where(
                predicates.toArray(Predicate[]::new)
        );
        countCriteria.where(
                predicates.toArray(Predicate[]::new)
        );
        countCriteria.select(cb.count(root));
        List<News> resultList = entityManager.createQuery(criteria)
                .setFirstResult(pageable.getPageSize() * (pageable.getPageNumber()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
        Long total = countQuery.getSingleResult();
        return PageableExecutionUtils.getPage(resultList, pageable, () -> total);
    }
}