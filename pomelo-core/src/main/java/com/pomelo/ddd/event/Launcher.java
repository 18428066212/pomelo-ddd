package com.pomelo.ddd.event;

import com.pomelo.ddd.enums.EventEmitWay;
import com.pomelo.ddd.manager.EventHandlerManager;
import com.pomelo.ddd.utils.ThreadPoolUtil;

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
