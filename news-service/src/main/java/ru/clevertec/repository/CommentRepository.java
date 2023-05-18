package ru.clevertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>,
        FilterCommentRepository,
        KeyValueRepository<Comment, Long> {

}
