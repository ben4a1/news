package ru.clevertec.cache;

import java.util.Map;

/**
 * Interface for custom caches
 *
 * @param <K> of course KEY
 * @param <V> accordingly VALUE (object)
 */
public interface Cache<K, V> {

    /**
     * Associates the specified value with the specified key in this cache.
     * If the cache previously contained a mapping for the key,
     * the old value is replaced
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     */
    void put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this cache contains no mapping for the key.
     *
     * @param key with which the specified value is to be associated
     * @return value to be associated with the specified key
     */
    V get(K key);

    /**
     * Removes the value for the specified key
     *
     * @param key with which the specified value is associated
     */
    void remove(K key);

    /**
     * Returns true if this cache contains a mapping for the specified
     * key. More formally, returns true if and only if this cache
     * contains a mapping for a key k such that Objects.equals(key, k).
     *
     * @param key whose presence in this map is to be tested
     * @return true if this cache contains a mapping for the specified key
     */
    boolean contains(K key);

    /**
     * Returns all values of cache
     *
     * @return Map of <Key, Value>
     */
    Map<K, V> getAll();
}
