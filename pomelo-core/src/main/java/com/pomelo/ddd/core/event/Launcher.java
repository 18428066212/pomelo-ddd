package com.pomelo.ddd.core.event;

import com.pomelo.ddd.core.enums.EventEmitWay;
import com.pomelo.ddd.core.manager.EventHandlerManager;
import com.pomelo.ddd.core.utils.EventHandleThreadPool;

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
                throw new InternalError(e.toString(), e);
            } catch (InvocationTargetException e) {
                Throwable th = e.getCause();
                if (th instanceof RuntimeException) {
                    throw (RuntimeException) th;
                } else {
                    throw new InternalError(th.toString(), th);
                }
            }
        });
    }

    public static <T> void emit(T t, EventEmitWay eventEmitWay) {

        Class<?> aClass = t.getClass();
        List<Method> methods = EventHandlerManager.getMethod(aClass);

        methods.forEach(method -> {
            if (EventEmitWay.ASYNC.equals(eventEmitWay)) {
                asyncHandle(t, method);
            } else {
                syncHandle(t, method);
            }
        });

    }

    private static <T> void syncHandle(T t, Method method) {
        execute(t, method);
    }


    private static <T> void asyncHandle(T t, Method method) {
        EventHandleThreadPool.executeAsync(() -> {
            execute(t, method);
        });
    }


    private static <T> void execute(T t, Method method) {
        try {
            method.invoke(EventHandlerManager.getObject(method.getDeclaringClass()), t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable th = e.getCause();
            if (th instanceof RuntimeException) {
                throw (RuntimeException) th;
            } else {
                throw new InternalError(th.toString(), th);
            }
        }
    }


}
