package ru.clevertec.factory.impl;

import org.springframework.beans.factory.annotation.Value;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LFUCache;
import ru.clevertec.factory.CacheFactory;

public class CacheFactoryLFUImpl<K, V> implements CacheFactory<K, V> {

    @Value("${cache.size}")
    int cacheSize;

    @Override
    public Cache<K, V> createCache() {
        return new LFUCache<>(cacheSize);
    }
}