package com.migrosone.couriertracking.cache;

import java.time.Duration;

public interface Cache<K, V> {

    void put(K key, V value);

    void put(K key, V value, Duration duration);

    V get(K key);

    boolean delete(String key);
}
