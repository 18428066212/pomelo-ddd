package com.pomelo.ddd.starter.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 何刚
 * @date 2020/8/8 14:06
 */
public final class Cache {

    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * 禁止其他包调用
     *
     * @param key
     * @param t
     * @param <T>
     */
    protected static <T> void add(String key, T t) {
        cache.put(key, t);
    }

    /**
     * 禁止其他包调用
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static <T> T get(String key) {
        return (T) cache.get(key);
    }

}
