package com.pomelo.ddd.core.entity;

import java.lang.reflect.Method;
import java.util.Map;

public class AggregateEntity {


    /**
     * 聚合
     */
    private Class<?> aggregate;

    /**
     * 初始化数据的方法
     */
    private Method loadMethod;

    /**
     * 命令对应的方法
     */
    private Map<Class<?>, Method> commandHandlerMap;


    public Class<?> getAggregate() {
        return aggregate;
    }

    public void setAggregate(Class<?> aggregate) {
        this.aggregate = aggregate;
    }

    public Method getLoadMethod() {
        return loadMethod;
    }

    public void setLoadMethod(Method loadMethod) {
        this.loadMethod = loadMethod;
    }

    public Map<Class<?>, Method> getCommandHandlerMap() {
        return commandHandlerMap;
    }

    public void setCommandHandlerMap(Map<Class<?>, Method> commandHandlerMap) {
        this.commandHandlerMap = commandHandlerMap;
    }
}
