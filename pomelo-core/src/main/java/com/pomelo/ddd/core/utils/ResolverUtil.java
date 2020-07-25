package com.pomelo.ddd.core.utils;

import com.pomelo.ddd.core.bean.AggregateResolver;
import com.pomelo.ddd.core.bean.Resolver;

import java.util.ServiceLoader;

public class ResolverUtil {

    private static Resolver resolver;

    static {
        ServiceLoader<Resolver> spiLoader = ServiceLoader.load(Resolver.class);
        int count = 0;
        for (Resolver annotationResolver : spiLoader) {
            if (count == 1) {
                //只能有一个解析器。
                break;
            }
            resolver = annotationResolver;
            count++;
        }
        if (count == 0) {
            resolver = new AggregateResolver();
        }
    }

    private ResolverUtil() {

    }

    public static void resolve(Object o) {
        resolver.resolve(o);
    }

}
