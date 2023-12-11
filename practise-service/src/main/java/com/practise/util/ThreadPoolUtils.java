package com.practise.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 线程池utils
 *
 * @author longyulan
 * @date 2023/6/19
 */
@Component
public class ThreadPoolUtils {

    private static ThreadPoolExecutor threadPoolExecutor;
    /**
     * 线程关闭等待时长
     */
    private static final Long KEEP_ALIVE_TIME = 60 * 1000L;
    /**
     * 线程池名称
     */
    private static final String POOL_NAME = "practise-thread-pool";
    /**
     * 等待队列长度
     */
    private static final int BLOCKING_QUEUE_LENGTH = 20;

    /**
     * 线程池执行任务
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     * 有返回值的线程池执行任务
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }

    /**
     * 获取线程池
     * @return
     */
    public static synchronized ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor != null) {
            return threadPoolExecutor;
        }
        int coreCount = Runtime.getRuntime().availableProcessors();
        int maxThreadSize = coreCount * 2;
        threadPoolExecutor = new ThreadPoolExecutor(
            coreCount,
            maxThreadSize,
            KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(BLOCKING_QUEUE_LENGTH),
            new ThreadFactoryBuilder().setNameFormat(POOL_NAME + "-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
