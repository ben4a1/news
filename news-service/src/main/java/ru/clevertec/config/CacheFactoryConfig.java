package ru.clevertec.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.factory.CacheFactory;
import ru.clevertec.factory.impl.CacheFactoryLFU;
import ru.clevertec.factory.impl.CacheFactoryLRU;

@Configuration
public class CacheFactoryConfig<K, V> {
    @Bean(name = "lruCache")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lru")
    public CacheFactory<K, V> cacheFactoryLRU() {
        return new CacheFactoryLRU<>();
    }

    @Bean(name = "lfuCache")
    @ConditionalOnProperty(prefix = "cache", name = "algorithm", havingValue = "lfu")
    public CacheFactory<K, V> cacheFactoryLFU() {
        return new CacheFactoryLFU<>();
    }
}
