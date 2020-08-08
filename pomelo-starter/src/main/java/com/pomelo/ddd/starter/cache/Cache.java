package com.pomelo.ddd.starter.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 何刚
 * @date 2020/8/8 14:06
 */
public class Cache {

    private static Map<String, Object> cache = new HashMap<>();


    public synchronized static <T> void add(String key, T t) {
        cache.put(key, t);
    }


    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) cache.get(key);
    }

}
