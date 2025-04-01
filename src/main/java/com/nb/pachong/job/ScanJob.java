package com.nb.pachong.job;

import com.nb.pachong.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScanJob {

    @Autowired
    ScanService scanService;

    @Scheduled(fixedRate = 20 * 60 * 1000) // 20min
    public void autoScan() {
        System.out.println("auto scan: " + new Date() + ",start--------------------->");

//        scanService.autoScan();

        System.out.println("auto scan: " + new Date() + ",end--------------------->");
    }
}
