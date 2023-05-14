package ru.clevertec.factory.impl;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LFUCache;
import ru.clevertec.factory.CacheFactory;

public class CacheFactoryLFUImpl<K, V> implements CacheFactory<K, V> {

    private final int cacheSize;

    public CacheFactoryLFUImpl(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public Cache<K, V> createCache() {
        return new LFUCache<>(cacheSize);
    }
}