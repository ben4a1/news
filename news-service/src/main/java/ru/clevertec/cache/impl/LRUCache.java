package ru.clevertec.cache.impl;

import ru.clevertec.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The class implements the LRU algorithm (a cache eviction
 * algorithm that arranges items in the order they are used)
 *
 * @param <K> – the type of keys maintained by this cache
 * @param <V> – the type of cached values
 */
public class LRUCache<K, V> implements Cache<K, V> {
    private final int cacheSize;
    private final Map<K, V> cacheData;

    /**
     * Initializing the cache with a given size.
     * Overriding a method with the condition
     * of deleting the "oldest" element.
     *
     * @param cacheSize - cache size
     */
    public LRUCache(int cacheSize) {
        cacheData = new LinkedHashMap<>(cacheSize, 1.2f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
        this.cacheSize = cacheSize;
    }

    @Override
    public void put(K key, V value) {
        cacheData.put(key, value);
    }

    @Override
    public V get(K key) {
        if (!cacheData.containsKey(key)) {
            return null;
        }
        V value = cacheData.get(key);
        cacheData.remove(key);
        cacheData.put(key, value);
        return value;
    }

    @Override
    public void remove(K key) {
        cacheData.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return cacheData.containsKey(key);
    }

    @Override
    public Map<K, V> getAll() {
        return cacheData;
    }
}
