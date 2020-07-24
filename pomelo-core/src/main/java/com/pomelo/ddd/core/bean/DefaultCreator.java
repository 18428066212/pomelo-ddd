package com.pomelo.ddd.core.bean;

public class DefaultCreator implements Creator {

    @Override
    public Object create(Class<?> aClass) {
        try {
            return aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
