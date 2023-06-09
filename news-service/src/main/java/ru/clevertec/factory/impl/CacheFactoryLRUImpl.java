package ru.clevertec.factory.impl;

import ru.clevertec.cache.Cache;
import ru.clevertec.cache.impl.LRUCache;
import ru.clevertec.factory.CacheFactory;

/**
 * Implementation for creating cache with LRU algorithm
 * {@link ru.clevertec.cache.impl.LRUCache}
 *
 * @param <K> of course KEY
 * @param <V> accordingly VALUE (object)
 */
public class CacheFactoryLRUImpl<K, V> implements CacheFactory<K, V> {

    private final int cacheSize;

    public CacheFactoryLRUImpl(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    public Cache<K, V> createCache() {
        return new LRUCache<>(cacheSize);
    }
}
