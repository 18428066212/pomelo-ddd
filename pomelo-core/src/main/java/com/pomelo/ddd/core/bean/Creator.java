package com.pomelo.ddd.core.bean;

import com.pomelo.ddd.core.utils.ResolverUtil;

public interface Creator {


    Object create(Class<?> aClass);

    default Object createProxy(Class<?> aClass) {
        Object o = create(aClass);
        ResolverUtil.resolve(o);
        return o;
    }

}
