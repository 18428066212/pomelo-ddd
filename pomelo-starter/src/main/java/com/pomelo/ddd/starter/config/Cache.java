package com.pomelo.ddd.starter.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 何刚
 * @date 2020/8/8 14:06
 */
public final class Cache {

    private static Map<String, Object> cache = new HashMap<>();

    /**
     * 禁止其他包调用
     *
     * @param key
     * @param t
     * @param <T>
     */
    protected synchronized static <T> void add(String key, T t) {
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
