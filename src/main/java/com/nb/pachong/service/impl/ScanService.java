package com.nb.pachong.service;

import com.nb.pachong.entity.ScanResultEntity;

import java.util.concurrent.CountDownLatch;

/**
 * ScanService
 */
public interface ScanService {

    /**
     * autoScan
     */
    void autoScan();

    /**
     * scan
     */
    ScanResultEntity scan(String url, CountDownLatch countDownLatch);

    /**
     * addScanUrl
     */
    boolean addScanUrl(String url);

    /**
     * delScanUrl
     */
    boolean delScanUrl(String url);
}
