package ru.clevertec.model;

import lombok.Getter;

@Getter
public enum CacheAlgorithm {

    LRU("lru"),
    LFU("lfu");

    private final String algorithm;

    CacheAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
