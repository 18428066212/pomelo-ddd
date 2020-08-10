package com.pomelo.ddd.starter.config;

import com.pomelo.ddd.starter.annotation.PomeloBasePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Objects;

public class PomeloListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PomeloListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Class<?> mainApplicationClass = event.getSpringApplication().getMainApplicationClass();
        PomeloBasePackage annotation = mainApplicationClass.getAnnotation(PomeloBasePackage.class);

        if (Objects.isNull(annotation)) {
            return;
        }
        Cache.add(Const.backPackage, annotation.basePackage());


    }
}
