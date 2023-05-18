package ru.clevertec.factory;

import ru.clevertec.cache.Cache;

public interface CacheFactory<K, V> {

    Cache<K, V> createCache();
}