package ru.clevertec.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    private String subject;

    @ManyToOne
    private User user;

    @JsonBackReference
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