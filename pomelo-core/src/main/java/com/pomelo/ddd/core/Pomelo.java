package com.pomelo.ddd.core;

import com.pomelo.ddd.core.annotation.Command;
import com.pomelo.ddd.core.entity.AggregateEntity;
import com.pomelo.ddd.core.exception.CommandNotFoundException;
import com.pomelo.ddd.core.exception.PomeloException;
import com.pomelo.ddd.core.manager.AggregateManager;
import com.pomelo.ddd.core.utils.CommandHandleThreadPool;
import com.pomelo.ddd.core.utils.ResolverUtil;

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
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new PomeloException("实例化聚合异常");
        }
    }


    public Pomelo<T> injectFiled() {
        ResolverUtil.resolve(this.t);
        return this;
    }

    public T stop() {
        return t;
    }

    public Pomelo<T> load(Object... params) {

        try {
            aggregateEntity.getLoadMethod().invoke(t, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new PomeloException("聚合执行LoadMethod异常");
        }
        return this;
    }

    /**
     * 执行命令，有返回值
     *
     * @param c
     * @param <R>
     * @param <C>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <R, C> R command(C c) {
        try {
            Command command = c.getClass().getAnnotation(Command.class);
            if (command == null) {
                throw new CommandNotFoundException("缺少command注解");
            }
            return (R) aggregateEntity.getCommandHandlerMap().get(c.getClass()).invoke(t, c);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new PomeloException("聚合执行LoadMethod异常");
        }
    }

    /**
     * 单程执行，且是异步执行
     *
     * @param c
     * @param <C>
     */
    public <C> void commandOneWay(C c) {

        Command command = c.getClass().getAnnotation(Command.class);
        if (command == null) {
            throw new CommandNotFoundException("缺少command注解");
        }
        CommandHandleThreadPool.executeAsync(() -> {
            try {
                aggregateEntity.getCommandHandlerMap().get(c.getClass()).invoke(t, c);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new PomeloException("聚合执行LoadMethod异常");
            }
        });
    }

}
