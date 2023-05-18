package ru.clevertec.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.factory.impl.CacheFactoryLFUImpl;
import ru.clevertec.factory.impl.CacheFactoryLRUImpl;

/**
 * Configuration class for custom caches with size and
 * algorithm taken from application.yml
 */
@Configuration
public class CacheFactoryConfig {

    private final int cacheSize;
    private final CacheAlgorithm cacheAlgorithm;

    public CacheFactoryConfig(@Value("${cache.size}") int cacheSize,
                              @Value("${cache.algorithm}") CacheAlgorithm cacheAlgorithm) {
        this.cacheSize = cacheSize;
        this.cacheAlgorithm = cacheAlgorithm;
    }

    /**
     * @return cache for CommentService
     */
    @Bean(name = "cacheCommentFactory")
    public CacheFactory<Long, Comment> cacheFactoryComment() {
        if ("lru".equalsIgnoreCase(cacheAlgorithm.getAlgorithm())) {
            return new CacheFactoryLRUImpl<>(cacheSize);
        } else if ("lfu".equalsIgnoreCase(cacheAlgorithm.getAlgorithm())) {
            return new CacheFactoryLFUImpl<>(cacheSize);
        }
        return null;
    }

    /**
     * @return cache for CommentService
     */
    @Bean(name = "cacheNewsFactory")
    public CacheFactory<Long, News> cacheFactoryNews() {
        if ("lru".equalsIgnoreCase(cacheAlgorithm.getAlgorithm())) {
            return new CacheFactoryLRUImpl<>(cacheSize);
        } else if ("lfu".equalsIgnoreCase(cacheAlgorithm.getAlgorithm())) {
            new CacheFactoryLFUImpl<>(cacheSize);
        }
        return null;
    }

    /**
     * ENUM possible algorithm cache type (in
     * the context of this application)
     */
    private enum CacheAlgorithm {

        LRU("lru"),
        LFU("lfu");

        @Getter
        private final String algorithm;

        CacheAlgorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }
}
