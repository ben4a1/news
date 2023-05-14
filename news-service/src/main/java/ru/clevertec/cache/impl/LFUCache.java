package ru.clevertec.cache.impl;


import ru.clevertec.cache.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * The class implements the LFU algorithm
 * (a cache storage algorithm that counts how
 * often each item is used and removes those that are least accessed)
 *
 * @param <K> – the type of keys maintained by this cache
 * @param <V> – the type of cached values
 */
public class LFUCache<K, V> implements Cache<K, V> {
    private final int cacheSize;
    private final Map<K, Node<K, V>> cacheData;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final int MAX = Integer.MAX_VALUE;
    private final int MIN = Integer.MIN_VALUE;

    public LFUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        this.cacheData = new HashMap<>();
        this.head = new Node<>(null, null, this.MAX);
        this.tail = new Node<>(null, null, this.MIN);
        head.next = tail;
        tail.prev = head;
    }

    public class Node<K, V> {
        public K key;
        public V value;
        public int count;
        public Node<K, V> prev;
        public Node<K, V> next;

        public Node(K key, V value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.prev = null;
            this.next = null;
        }
    }

    private void move(Node node) {
        Node curr = head;
        while (curr != null) {
            if (curr.count > node.count) {
                curr = curr.next;
            } else {
                node.prev = curr.prev;
                node.next = curr;
                node.next.prev = node;
                node.prev.next = node;
                break;
            }
        }
    }

    @Override
    public void put(K key, V value) {
        if (cacheData.containsKey(key)) {
            return;
        }
        if (cacheData.size() == cacheSize) {
            cacheData.remove(tail.prev.key);
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
        }
        Node newNode = new Node(key, value, 0);
        cacheData.put(key, newNode);
        move(newNode);
    }

    @Override
    public V get(K key) {
        if (!cacheData.containsKey(key)) {
            return null;
        }
        Node<K, V> current = cacheData.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.count++;
        move(current);
        return cacheData.get(key).value;
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
        Map<K, V> map = new HashMap<>(cacheData.size());
        cacheData.forEach((k, vNode) -> map.put(k, vNode.value));
        return map;
    }
}
