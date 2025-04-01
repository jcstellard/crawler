package com.nb.pachong.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolUtil
 */
public class ThreadPoolUtil {

    private static ExecutorService mainThreadPool;

    private static ExecutorService oneThreadPool;

    private static ExecutorService twoThreadPool;

    private static ExecutorService threeThreadPool;

    static {
        mainThreadPool =  new ThreadPoolExecutor(
                50, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        oneThreadPool =  new ThreadPoolExecutor(
                50, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


        twoThreadPool =  new ThreadPoolExecutor(
                50, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


        threeThreadPool =  new ThreadPoolExecutor(
                50, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    }

    public static ExecutorService getMainThreadPool() {
        return mainThreadPool;
    }

    public static ExecutorService getOneThreadPool() {
        return oneThreadPool;
    }

    public static ExecutorService getTwoThreadPool() {
        return twoThreadPool;
    }

    public static ExecutorService getThreeThreadPool() {
        return threeThreadPool;
    }
}
