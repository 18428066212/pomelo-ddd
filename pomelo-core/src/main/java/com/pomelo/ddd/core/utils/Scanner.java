package com.pomelo.ddd.core.utils;

import com.pomelo.ddd.core.annotation.Aggregate;
import com.pomelo.ddd.core.annotation.CommandHandler;
import com.pomelo.ddd.core.annotation.EventHandler;
import com.pomelo.ddd.core.annotation.LoadMethod;
import com.pomelo.ddd.core.entity.AggregateEntity;
import com.pomelo.ddd.core.manager.AggregateManager;
import com.pomelo.ddd.core.manager.EventHandlerManager;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Scanner {

    @SuppressWarnings("unchecked")
    public static void scan(String backPackage) {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(backPackage)
                .addScanners(new FieldAnnotationsScanner())
                .addScanners(new MethodAnnotationsScanner())
                .addScanners(new MethodParameterScanner()));

        scanAggregate(reflections);

        scanEventHandler(reflections);

    }

    private static void scanEventHandler(Reflections reflections) {
        Set<Method> methodsAnnotatedWithSet = reflections.getMethodsAnnotatedWith(EventHandler.class);

        for (Method method : methodsAnnotatedWithSet) {

            Class<?> declaringClass = method.getDeclaringClass();

            Object o = null;
            try {
                //是否从Spring 容器中获取
                o = declaringClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            EventHandler annotation = method.getAnnotation(EventHandler.class);
            Class<?> value = annotation.value();

            EventHandlerManager.addObject(declaringClass, o);
            EventHandlerManager.addMethod(value, method);
        }

    }

    @SuppressWarnings("unchecked")
    private static void scanAggregate(Reflections reflections) {
        Set<Class<?>> aggregateClassSet = reflections.getTypesAnnotatedWith(Aggregate.class);
        for (Class<?> aClass : aggregateClassSet) {
            AggregateEntity aggregateEntity = new AggregateEntity();
            aggregateEntity.setAggregate(aClass);
            Set<Method> allMethods = ReflectionUtils.getAllMethods(aClass);
            Method loadMethod = null;
            int loadMethodCount = 0;
            Map<Class<?>, Method> commandMethod = new HashMap<>();
            for (Method method : allMethods) {
                LoadMethod loadMethodAnnotation = method.getAnnotation(LoadMethod.class);
                if (loadMethodAnnotation != null) {
                    loadMethodCount++;
                    loadMethod = method;
                    continue;
                }
                CommandHandler commandHandlerAnnotation = method.getAnnotation(CommandHandler.class);
                if (commandHandlerAnnotation != null) {
                    commandMethod.put(commandHandlerAnnotation.value(), method);
                }
            }
            if (loadMethodCount == 0) {
                throw new RuntimeException(aClass.getName() + "没有找到 @LoadMethod 标记的方法");
            }
            if (loadMethodCount > 1) {
                throw new RuntimeException(aClass.getName() + "只能有一个 @LoadMethod 标记的方法");
            }
            aggregateEntity.setLoadMethod(loadMethod);
            aggregateEntity.setCommandHandlerMap(commandMethod);
            AggregateManager.add(aggregateEntity);
        }
    }
}
