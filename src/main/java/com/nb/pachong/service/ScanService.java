package com.nb.pachong.service;

import com.nb.pachong.entity.ScanResultEntity;

import java.util.concurrent.CountDownLatch;

/**
 * 扫描Service
 */
public interface ScanService {

    /**
     * 自动扫描
     */
    void autoScan();

    /**
     * 扫描单个url
     */
    ScanResultEntity scan(String url, CountDownLatch countDownLatch);

    /**
     * 添加扫描url
     */
    boolean addScanUrl(String url);

    /**
     * 删除扫描url
     */
    boolean delScanUrl(String url);
}
