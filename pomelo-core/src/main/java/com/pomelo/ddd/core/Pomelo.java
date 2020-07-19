package com.pomelo.ddd.core;

import com.pomelo.ddd.core.entity.AggregateEntity;
import com.pomelo.ddd.core.manager.AggregateManager;

import java.lang.reflect.InvocationTargetException;

public class Pomelo<T> {

    private T t;

    private final AggregateEntity aggregateEntity;

    @SuppressWarnings("unchecked")
    public Pomelo(Class<T> clazz) {
        AggregateEntity aggregateEntity = AggregateManager.get(clazz);
        this.aggregateEntity = aggregateEntity;
        try {
            Object o = aggregateEntity.getAggregate().newInstance();
            t = (T) o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public Pomelo<T> load(Object... params) {

        try {
            aggregateEntity.getLoadMethod().invoke(t, params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public <R, C> R send(C c) {
        try {
            return (R) aggregateEntity.getCommandHandlerMap().get(c.getClass()).invoke(t, c);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
