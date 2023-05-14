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
import ru.clevertec.dto.CommentFilter;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.Comment_;
import ru.clevertec.entity.News_;
import ru.clevertec.entity.User_;
import ru.clevertec.repository.FilterCommentRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterCommentRepositoryImpl implements FilterCommentRepository {

    private final EntityManager entityManager;

    @Override
    public Page<Comment> findAll(@NonNull CommentFilter filter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteria = cb.createQuery(Comment.class);
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Comment> comment = criteria.from(Comment.class);
        Predicate[] predicates = getPredicateArray(filter, cb, comment);
        criteria.select(comment)
                .where(predicates);
        countQuery.select(cb.count(comment))
                .where(predicates);
        List<Comment> resultList = entityManager.createQuery(criteria)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        long total = getCommentCount(cb, filter);
        return PageableExecutionUtils.getPage(resultList, pageable, () -> total);
    }

    private Predicate[] getPredicateArray(CommentFilter filter, CriteriaBuilder cb, Root<Comment> comment) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.subject() != null) {
            predicates.add(cb.like(comment.get(Comment_.SUBJECT), "%" + filter.subject() + "%"));
        }
        if (filter.username() != null) {
            predicates.add(cb.like(comment.get(Comment_.user).get(User_.USERNAME), "%" + filter.username() + "%"));
        }
        if (filter.newsId() != null) {
            predicates.add(cb.equal(comment.get(Comment_.news).get(News_.ID), filter.newsId()));
        }
        return predicates.toArray(Predicate[]::new);
    }

    private long getCommentCount(CriteriaBuilder criteriaBuilder, CommentFilter filter) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Comment> countRoot = countQuery.from(Comment.class);
        Predicate[] predicates = getPredicateArray(filter, criteriaBuilder, countRoot);
        countQuery.select(criteriaBuilder.count(countRoot))
                .where(predicates);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}