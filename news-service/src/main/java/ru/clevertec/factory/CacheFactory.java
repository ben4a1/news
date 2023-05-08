package ru.clevertec.factory;

import org.springframework.stereotype.Component;
import ru.clevertec.cache.Cache;

@Component
public interface CacheFactory<K, V> {
    Cache<K, V> createCache();
}
