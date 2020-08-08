package com.pomelo.ddd.core.event;

import com.pomelo.ddd.core.enums.EventEmitWay;
import com.pomelo.ddd.core.exception.PomeloException;
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
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new PomeloException("Pomelo Exception");
            }
        });
    }

    public static <T> void emit(T t, EventEmitWay eventEmitWay) {

        Class<?> aClass = t.getClass();
        List<Method> methods = EventHandlerManager.getMethod(aClass);

        methods.forEach(method -> {

            if (EventEmitWay.ASYNC.equals(eventEmitWay)) {

                ThreadPoolUtil.executeAsync(() -> {
                    try {
                        method.invoke(EventHandlerManager.getObject(method.getDeclaringClass()), t);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        throw new PomeloException("Pomelo Exception");
                    }
                });

            } else {
                try {
                    method.invoke(EventHandlerManager.getObject(method.getDeclaringClass()), t);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    throw new PomeloException("Pomelo Exception");
                }
            }


        });

    }

}
