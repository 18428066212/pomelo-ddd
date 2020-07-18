package com.pomelo.ddd.core.manager;

import com.pomelo.ddd.core.entity.AggregateEntity;

import java.util.HashMap;
import java.util.Map;

public class AggregateManager {

    private static final Map<Class<?>, AggregateEntity> aggregateEntityMap = new HashMap<>();

    public static void add(AggregateEntity aggregateEntity) {
        aggregateEntityMap.put(aggregateEntity.getAggregate(), aggregateEntity);
    }

    public static AggregateEntity get(Class<?> clazz) {
        return aggregateEntityMap.get(clazz);
    }
}
