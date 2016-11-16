package com.kidbear.logical.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author 何金成
 * @ClassName: ExecutorPool
 * @Description: 线程池管理
 * @date 2016年4月18日 下午3:42:42
 */
public class ExecutorPool {
    public static ExecutorService channelHandleThread = null;
    public static ExecutorService eventThread = null;
    // public static ExecutorService msgHandleThread = null;
    public static ExecutorService eventLogThread = null;
    public static ExecutorService payLogThread = null;
    public static ExecutorService operateLogThread = null;
    public static ExecutorService ipLogThread = null;
    public static ExecutorService emailThread = null;

    public static Logger logger = LoggerFactory.getLogger(ExecutorPool.class);

    public static void initThreadsExecutor() {
        channelHandleThread = initCachedThread("channelHandleThreadPool");
        eventThread = initSingleThread("eventThread");
        // msgHandleThread = initSingleThread("msgHandleThread");
        eventLogThread = initSingleThread("eventLogThread");
        payLogThread = initSingleThread("payLogThread");
        operateLogThread = initSingleThread("operateLogThread");
        ipLogThread = initSingleThread("ipLogThread");
        emailThread = initCachedThread("emailThread");
    }

    public static ExecutorService initSingleThread(String name) {
        ExecutorService service = Executors
                .newSingleThreadExecutor(new ThreadFactoryBuilder()
                        .setNameFormat(name + "-%d")
                        .setUncaughtExceptionHandler(
                                new ThreadPoolExceptionHandler()).build());
        logger.info("线程池{}初始化", name);
        return service;
    }

    /**
     * @param service
     * @param name    void
     * @throws
     * @Title: initThread
     * @Description: 线程池初始化
     */
    public static ExecutorService initCachedThread(String name) {
        ExecutorService service = Executors
                .newCachedThreadPool(new ThreadFactoryBuilder()
                        .setNameFormat(name + "-%d")
                        .setUncaughtExceptionHandler(
                                new ThreadPoolExceptionHandler()).build());
        logger.info("线程池{}初始化", name);
        return service;
    }

    public static void shutdown() {
        shutdown(channelHandleThread);
        shutdown(eventThread);
        // shutdown(msgHandleThread);
        shutdown(eventLogThread);
        shutdown(payLogThread);
        shutdown(operateLogThread);
        shutdown(ipLogThread);
        shutdown(emailThread);
    }

    /**
     * @param service void
     * @throws
     * @Title: shutdown
     * @Description: 关闭线程池
     */
    private static void shutdown(ExecutorService service) {
        if (!service.isShutdown()) {
            service.shutdown();
        }
    }
}
