package com.nb.pachong.service.impl;

import com.alibaba.fastjson.JSON;
import com.nb.pachong.dao.ScanUrlDao;
import com.nb.pachong.entity.ScanResultEntity;
import com.nb.pachong.service.CaService;
import com.nb.pachong.service.ScanService;
import com.nb.pachong.service.XAddressService;
import com.nb.pachong.util.ScanUtil;
import com.nb.pachong.util.ThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ScanServiceImpl
 */
@Service
public class ScanServiceImpl implements ScanService {

    @Autowired
    private ScanUrlDao scanUrlDao;

    @Autowired
    private CaService caService;

    @Autowired
    private XAddressService xAddressService;

    /**
     * autoScan
     */
    @Override
    public void autoScan() {
        List<String> scanUrls = scanUrlDao.getScanUrls();

        if (scanUrls == null) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(scanUrls.size());

        for (String scanUrl : scanUrls) {
            ThreadPoolUtil.getMainThreadPool().execute(() -> {
                scan(scanUrl, countDownLatch);
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * scan
     */
    @Override
    public ScanResultEntity scan(String url, CountDownLatch countDownLatch) {
        ScanResultEntity scan = ScanUtil.scan(url, countDownLatch);
        System.out.println(JSON.toJSONString(scan));

        if (scan == null) {
            return null;
        }

        //addCa
        caService.addCa(scan.getCaRes(), scan.getDate());

        //addXAddress
        xAddressService.addXAddress(scan.getxAddressRes(), scan.getDate());

        return scan;
    }

    /**
     * addScanUrl
     */
    @Override
    public boolean addScanUrl(String url) {
        List<String> scanUrls = scanUrlDao.getScanUrls();
        if (scanUrls.contains(url)){
            return scanUrlDao.update(url, new Date());
        }else {
            return scanUrlDao.addUrl(url, new Date());
        }
    }

    /**
     * delScanUrl
     */
    @Override
    public boolean delScanUrl(String url) {
        return scanUrlDao.deleteUrl(url, new Date());
    }
}
