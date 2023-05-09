package ru.clevertec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.entity.Comment;
import ru.clevertec.entity.News;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.factory.impl.CacheFactoryLFUImpl;
import ru.clevertec.factory.impl.CacheFactoryLRUImpl;

@Configuration
public class CacheFactoryConfig {

    private final int cacheSize;
    private final String cacheAlgorithm;

    public CacheFactoryConfig(@Value("${cache.size}") int cacheSize,
                              @Value("${cache.algorithm}") String cacheAlgorithm) {
        this.cacheSize = cacheSize;
        this.cacheAlgorithm = cacheAlgorithm;
    }

    @Bean(name = "cacheCommentFactory")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lru")
    public CacheFactory<Long, Comment> cacheFactoryComment() {
        return "lru".equalsIgnoreCase(cacheAlgorithm)
                ? new CacheFactoryLRUImpl<>(cacheSize)
                : new CacheFactoryLFUImpl<>(cacheSize);
    }

    @Bean(name = "cacheNewsFactory")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lru")
    public CacheFactory<Long, News> cacheFactoryNews() {
        return "lru".equalsIgnoreCase(cacheAlgorithm)
                ? new CacheFactoryLRUImpl<>(cacheSize)
                : new CacheFactoryLFUImpl<>(cacheSize);
    }
}
