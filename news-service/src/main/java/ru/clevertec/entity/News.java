package ru.clevertec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class News implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    private String title;

    private String subject;

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
