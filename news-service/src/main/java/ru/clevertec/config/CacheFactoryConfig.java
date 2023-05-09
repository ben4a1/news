package ru.clevertec.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.factory.impl.CacheFactoryLFUImpl;
import ru.clevertec.factory.impl.CacheFactoryLRUImpl;

@Configuration
public class CacheFactoryConfig<K, V> {
    @Bean(name = "lruCache")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lru")
    public CacheFactory<K, V> cacheFactoryLRU() {
        return new CacheFactoryLRUImpl<>();
    }

    @Bean(name = "lfuCache")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lfu")
    public CacheFactory<K, V> cacheFactoryLFU() {
        return new CacheFactoryLFUImpl<>();
    }
}
