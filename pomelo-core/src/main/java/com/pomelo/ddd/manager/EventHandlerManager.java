package com.pomelo.ddd.manager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandlerManager {

    private static final Map<Class<?>, List<Method>> methodMap = new HashMap<>();

    private static final Map<Class<?>, Object> objectMap = new HashMap<>();





    public static void addMethod(Class<?> aClass, Method method) {
        if (methodMap.get(aClass) == null) {

            methodMap.put(aClass, new ArrayList<Method>() {
                {
                    add(method);
                }
            });
        } else {
            methodMap.get(aClass).add(method);
        }
    }

    public static List<Method> getMethod(Class<?> aClass) {
        return methodMap.get(aClass);
    }

    public static void addObject(Class<?> aClass, Object o) {
        objectMap.put(aClass, o);
    }


    public static Object getObject(Class<?> aClass) {
        return objectMap.get(aClass);
    }


}
