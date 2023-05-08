package ru.clevertec.factory.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LRUCache;
import ru.clevertec.factory.CacheFactory;

@Component
public class CacheFactoryLRU<K, V> implements CacheFactory<K, V> {

    @Value("${cache.size}")
    int cacheSize;

    @Override
    public Cache<K, V> createCache() {
        return new LRUCache<>(cacheSize);
    }
}
