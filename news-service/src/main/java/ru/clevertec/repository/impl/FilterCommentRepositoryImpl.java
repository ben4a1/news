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
        CriteriaQuery<Long> criteriaT = cb.createQuery(Long.class);
        Root<Comment> comment = criteria.from(Comment.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.subject() != null) {
            predicates.add(cb.like(comment.get(Comment_.SUBJECT), "%" + filter.subject() + "%"));
        }
        if (filter.username() != null) {
            predicates.add(cb.equal(comment.get(Comment_.user).get(User_.USERNAME), filter.username()));
        }
        if (filter.newsId() != null) {
            predicates.add(cb.equal(comment.get(Comment_.news).get(News_.ID), filter.newsId()));
        }
        criteria.select(comment).where(
                predicates.toArray(Predicate[]::new)
        );
        List<Comment> resultList = entityManager.createQuery(criteria)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        Long total = entityManager.createQuery(criteriaT.select(cb.count(criteriaT.from(Comment.class)))).getSingleResult();
        return PageableExecutionUtils.getPage(resultList, pageable, () -> total);
    }
}
