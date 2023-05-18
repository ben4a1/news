package ru.clevertec.entity;

import java.io.Serializable;

/**
 * @param <T> type of identifier type
 */
public interface BaseEntity<T extends Serializable> {

    T getId();

    void setId(T id);
}
