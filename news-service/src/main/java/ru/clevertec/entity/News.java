package ru.clevertec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO class for storing news in the database
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class News implements BaseEntity<Long> {

    /**
     * News unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * News creation time
     */
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    /**
     * News headline
     */
    private String title;

    /**
     * News content
     */
    private String subject;

    /**
     * Collection of comments related to the news
     */
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @Builder.Default
    private final List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "News{" +
               "id=" + id +
               ", creationTime=" + creationTime +
               ", title='" + title + '\'' +
               ", subject='" + subject + '\'' +
               ", comments=" + comments +
               '}';
    }
}
