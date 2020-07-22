package com.pomelo.ddd.core.event;

import com.pomelo.ddd.core.enums.EventEmitWay;
import com.pomelo.ddd.core.manager.EventHandlerManager;
import com.pomelo.ddd.core.utils.ThreadPoolUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Launcher {

    public static <T> void emit(T t) {

        Class<?> aClass = t.getClass();
        List<Method> methods = EventHandlerManager.getMethod(aClass);

        methods.forEach(method -> {
            try {
                method.invoke(EventHandlerManager.getObject(aClass), t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    public static <T> void emit(T t, EventEmitWay eventEmitWay) {

        Class<?> aClass = t.getClass();
        List<Method> methods = EventHandlerManager.getMethod(aClass);

        methods.forEach(method -> {

            if (EventEmitWay.SYNC.equals(eventEmitWay)) {

                ThreadPoolUtil.executeAsync(() -> {
                    try {
                        method.invoke(EventHandlerManager.getObject(method.getDeclaringClass()), t);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

            } else {
                try {
                    method.invoke(EventHandlerManager.getObject(method.getDeclaringClass()), t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }


        });

    }

}
