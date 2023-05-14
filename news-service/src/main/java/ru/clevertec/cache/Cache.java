package ru.clevertec.cache;

import org.springframework.stereotype.Component;

import java.util.Map;

public interface Cache<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean contains(K key);
    Map<K, V> getAll();
}
