package com.pomelo.ddd.starter.config;

import com.pomelo.ddd.starter.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class PomeloScanListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PomeloScanListener.class);

    private boolean scan = false;

    @Override
    public synchronized void onApplicationEvent(ContextRefreshedEvent event) {
        if (scan) {
            return;
        }
        SpringUtil.setApplicationContext(event.getApplicationContext());
        Ready.applicationContextReady();
        scan = true;

    }
}
