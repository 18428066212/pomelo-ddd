package com.pomelo.ddd.starter.config;

import com.pomelo.ddd.core.utils.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 何刚
 * @date 2020/8/10 14:42
 */
public final class ScannerForSpring {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScannerForSpring.class);

    protected static void scan() {
        LOGGER.info("开始扫描");
        long start = System.currentTimeMillis();
        Scanner.scan(Cache.get(Const.backPackage));
        LOGGER.info("扫描结束，耗时:{}ms", System.currentTimeMillis() - start);
    }
}
