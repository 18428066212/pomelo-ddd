package com.pomelo.ddd.starter.config;

import com.pomelo.ddd.core.utils.Scanner;
import com.pomelo.ddd.starter.cache.Cache;
import com.pomelo.ddd.starter.cache.Const;
import com.pomelo.ddd.starter.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class PomeloScanListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PomeloScanListener.class);

    private final boolean scan = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (scan) {
            return;
        }
        SpringUtil.setApplicationContext(event.getApplicationContext());

        LOGGER.info("开始扫描");
        long start = System.currentTimeMillis();
        Scanner.scan(Cache.get(Const.backPackage));
        LOGGER.info("扫描结束，耗时:{}ms", System.currentTimeMillis() - start);

    }
}
