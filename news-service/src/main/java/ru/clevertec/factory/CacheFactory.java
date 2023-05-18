package ru.clevertec.factory;

import ru.clevertec.cache.Cache;

/**
 * Abstract factory for creating custom cache
 *
 * @param <K> of course KEY
 * @param <V> accordingly VALUE (object)
 */
public interface CacheFactory<K, V> {

    /**
     * @return {@link ru.clevertec.cache.Cache}
     */
    Cache<K, V> createCache();
}
