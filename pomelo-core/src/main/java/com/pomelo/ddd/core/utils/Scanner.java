package com.pomelo.ddd.core.utils;

import com.pomelo.ddd.core.annotation.Aggregate;
import com.pomelo.ddd.core.annotation.CommandHandler;
import com.pomelo.ddd.core.annotation.EventHandler;
import com.pomelo.ddd.core.annotation.LoadMethod;
import com.pomelo.ddd.core.bean.Creator;
import com.pomelo.ddd.core.bean.DefaultCreator;
import com.pomelo.ddd.core.entity.AggregateEntity;
import com.pomelo.ddd.core.exception.PomeloException;
import com.pomelo.ddd.core.manager.AggregateManager;
import com.pomelo.ddd.core.manager.EventHandlerManager;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

public class Scanner {

    private static Creator creator;

    private Scanner() {

    }

    static {
        ServiceLoader<Creator> spiLoader = ServiceLoader.load(Creator.class);
        int count = 0;
        for (Creator c : spiLoader) {
            if (count == 1) {
                throw new RuntimeException("只需要一个Creator");
            }
            count++;
            creator = c;
        }

        if (count == 0) {
            creator = new DefaultCreator();
        }

    }


    @SuppressWarnings("unchecked")
    public static void scan(String backPackage) {

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(backPackage)
                .addScanners(new FieldAnnotationsScanner())
                .addScanners(new MethodAnnotationsScanner())
                .addScanners(new SubTypesScanner())
                .addScanners(new TypeAnnotationsScanner())
                .addScanners(new MethodParameterScanner()));

        scanAggregate(reflections);

        scanEventHandler(reflections);

    }

    private static void scanEventHandler(Reflections reflections) {
        Set<Method> methodsAnnotatedWithSet = reflections.getMethodsAnnotatedWith(EventHandler.class);

        for (Method method : methodsAnnotatedWithSet) {

            Class<?> declaringClass = method.getDeclaringClass();
            Object o = creator.createProxy(declaringClass);

            EventHandler annotation = method.getAnnotation(EventHandler.class);
            Class<?> value = annotation.value();

            EventHandlerManager.addObject(declaringClass, o);
            EventHandlerManager.addMethod(value, method);
        }

    }

    @SuppressWarnings("unchecked")
    private static void scanAggregate(Reflections reflections) {
        Set<Class<?>> aggregateClassSet = reflections.getTypesAnnotatedWith(Aggregate.class, true);
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
                throw new PomeloException(aClass.getName() + " 没有找到 @LoadMethod 标记的方法");
            }
            if (loadMethodCount > 1) {
                throw new PomeloException(aClass.getName() + " 只能有一个 @LoadMethod 标记的方法");
            }
            aggregateEntity.setLoadMethod(loadMethod);
            aggregateEntity.setCommandHandlerMap(commandMethod);
            AggregateManager.add(aggregateEntity);
        }
    }
}
