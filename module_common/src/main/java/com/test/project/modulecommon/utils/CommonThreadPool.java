package com.test.project.modulecommon.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc: 通用线程池管理，主要获取异步任务线程池和刷新UI相关的线程池，避免创建过多的线程池
 * <pre>
 *     注意：通用线程池主要用于分布广、频率小、耗时比较短的操作，这些异步任务可能分散在各个类或库中
 *     以此避免因为一些耗时过长的任务导致后续任务长时间无法执行
 *     耗时长或者频繁的任务请另创建新的线程池自己维护
 * </pre>
 */

public class CommonThreadPool {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private CommonThreadPool(){
        // private construct
    }

    private static class TaskExecutorServiceHolder {
        private static final ThreadPoolExecutor sInstance = new ThreadPoolExecutor(CPU_COUNT, CPU_COUNT,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    }

    private static class UIExecutorServiceHolder {
        private static final ThreadPoolExecutor sInstance = new ThreadPoolExecutor(CPU_COUNT, CPU_COUNT,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    }

    private static class SingleTaskExecutorServiceHolder {
        private static final ExecutorService sInstance = Executors.newSingleThreadExecutor();
    }

    /**
     * 获取用于UI刷新相关的UI逻辑耗时异步任务，且任务是频率小而耗时短的
     * <pre>
     *     注意：
     *     1.此线程池创建多个任务用于并发执行
     *     2.通用线程池主要用于分布广、频率小、耗时比较短的操作，这些异步任务可能分散在各个类或库中
     *     以此避免因为一些耗时过长的任务导致后续任务长时间无法执行
     *     耗时长或者频繁的任务请另创建新的线程池自己维护
     * </pre>
     * @return
     */
    public synchronized static ThreadPoolExecutor getUIThreadPool(){
        return UIExecutorServiceHolder.sInstance;
    }

    /**
     * 获取用于非UI相关的耗时异步任务，且任务是频率小而耗时短的
     * <pre>
     *     注意：
     *     1.此线程池创建多个任务用于并发执行
     *     2.通用线程池主要用于分布广、频率小、耗时比较短的操作，这些异步任务可能分散在各个类或库中
     *     以此避免因为一些耗时过长的任务导致后续任务长时间无法执行
     *     耗时长或者频繁的任务请另创建新的线程池自己维护
     * </pre>
     * @return
     */
    public static ThreadPoolExecutor getTaskThreadPool(){
        return TaskExecutorServiceHolder.sInstance;
    }

    /**
     * 获取用于非UI相关的耗时异步任务，且任务是频率小而耗时短的
     * <pre>
     *     注意：
     *     1.此线程池顺序执行
     *     2.通用线程池主要用于分布广、频率小、耗时比较短的操作，这些异步任务可能分散在各个类或库中
     *     以此避免因为一些耗时过长的任务导致后续任务长时间无法执行
     *     耗时长或者频繁的任务请另创建新的线程池自己维护
     * </pre>
     * @return
     */
    public static ExecutorService getSingleTaskThreadPool(){
        return SingleTaskExecutorServiceHolder.sInstance;
    }

}
