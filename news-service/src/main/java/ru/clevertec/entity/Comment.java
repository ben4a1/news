package ru.clevertec.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * POJO class for storing comments in the database
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment implements BaseEntity<Long> {

    /**
     * Comment unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Comment creation time
     */
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    /**
     * Comment content
     */
    private String subject;

    /**
     * The user who left the comment
     * <p>
     * ManyToOne relationship (many comments - one user)
     */
    @ManyToOne
    private User user;

    /**
     * The news to which the comment relates
     * <p>
     * ManyToOne relationship (many comments - one user)
     */
    @ManyToOne
    private News news;

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", creationTime=" + creationTime +
               ", subject='" + subject + '\'' +
               ", user=" + user.getUsername() +
               ", newsId=" + news.getId() +
               '}';
    }
}
