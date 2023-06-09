package ru.clevertec.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.clevertec.cache.impl.LRUCache;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LRUCacheTest {

    private static Cache<String, Integer> lruCache;

    @BeforeAll
    static void prepare() {
        lruCache = new LRUCache<>(4);
        lruCache.put("one", 1);
        lruCache.get("one");
        lruCache.put("two", 2);
        lruCache.put("three", 3);
        lruCache.put("four", 4);
        lruCache.put("five", 5);
    }

    @Test
    void checkGetShouldReturn2() {
        assertThat(lruCache.get("two")).isEqualTo(2);
    }

    @Test
    void checkGetShouldReturnNull() {
        assertThat(lruCache.get("one")).isNull();
    }

    @Test
    void checkGetAllShouldReturnSameSize() {
        Map<String, Integer> before = lruCache.getAll();

        lruCache.put("eleven", 11);
        Map<String, Integer> after = lruCache.getAll();

        assertThat(after).isEqualTo(before);
    }
}
