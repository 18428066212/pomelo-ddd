package com.pomelo.ddd.core.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class EventHandleThreadPool {

//region private var

    private EventHandleThreadPool() {

    }

    private static final int corePoolSize;

    private static final int maximumPoolSize;
    private static final int keepAliveTime = 30;

    static {
        corePoolSize = 2 * Runtime.getRuntime().availableProcessors();
        maximumPoolSize = corePoolSize * 2;
    }

    private static final TimeUnit timeUnit = TimeUnit.MINUTES;

    private static final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>(4000);

    private static final ThreadPoolExecutor threadPoolExecutor;

    static {

        threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                timeUnit,
                blockingQueue,
                new ThreadFactoryBuilder().setNameFormat("pomelo-ddd-event-handle-%d").build()
        );
    }


    public static void executeAsync(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public static void shutdown() {
        threadPoolExecutor.shutdown();
    }

//endregion

//region public methods


//endregion

//region private methods


//endregion

}
