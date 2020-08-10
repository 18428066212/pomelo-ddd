package com.pomelo.ddd.starter.config;

/**
 * @author 何刚
 * @date 2020/8/10 14:40
 */
public final class Ready {

    private static boolean applicationContextReady;

    private static boolean basePackageReady;

    protected static void applicationContextReady() {
        Ready.applicationContextReady = true;
        scan();
    }

    protected static void basePackageReady() {
        Ready.basePackageReady = true;
        scan();
    }


    private static void scan() {
        if (applicationContextReady && basePackageReady) {
            ScannerForSpring.scan();
        }
    }


}
