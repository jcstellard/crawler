package com.nb.pachong.controller;

import com.nb.pachong.entity.CaEntity;
import com.nb.pachong.entity.Result;
import com.nb.pachong.entity.XEntity;
import com.nb.pachong.service.CaService;
import com.nb.pachong.service.ScanService;
import com.nb.pachong.service.XAddressService;
import com.nb.pachong.util.DataTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ScanService scanService;

    @Autowired
    private CaService caService;

    @Autowired
    private XAddressService xAddressService;

    @RequestMapping("/autoScan")
    public Result autoScan() {
        long start = System.currentTimeMillis();
        scanService.autoScan();
        return Result.success(System.currentTimeMillis() - start);
    }

    @RequestMapping("/scanUrl")
    public Result scanUrl(@RequestParam Map<String, Object> params) {
        String url = DataTransUtil.null2String(params.get("url"));
        if (StringUtils.isEmpty(url)) {
            return Result.fail("url is empty");
        }
        return Result.success(scanService.scan(url, new CountDownLatch(1)));
    }

    @RequestMapping("/getCa")
    public Object getCa(@RequestParam Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        List<CaEntity> ca = caService.getCa(params);
        if (ca.size() == 0) {
            return Result.fail("no data");
        }
        res.put("data", ca);
        res.put("total", ca.size());
        return res;
    }

    @RequestMapping("/getCaByPage")
    public Object getCaByPage(@RequestParam Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", caService.getCaByPage(params));
        res.put("total", caService.getCa(params).size());
        return res;
    }

    @RequestMapping("/getX")
    public Object getX(@RequestParam Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        List<XEntity> xs = xAddressService.getX(params);
        if (xs.size() == 0) {
            return Result.fail("no data");
        }
        res.put("data", xs);
        res.put("total", xs.size());
        return res;
    }

    @RequestMapping("/getXByPage")
    public Object getXByPage(@RequestParam Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        res.put("data", xAddressService.getXByPage(params));
        res.put("total", xAddressService.getX(params).size());
        return res;
    }

    @RequestMapping("/getCaAndX")
    public Object getCaAndX(@RequestParam Map<String, Object> params) {
        Map<String, Object> res = new HashMap<>();
        List<CaEntity> ca = caService.getCa(params);
        res.put("ca", ca);
        res.put("caTotal", ca.size());

        List<XEntity> xs = xAddressService.getX(params);
        res.put("x", xs);
        res.put("xTotal", xs.size());
        return res;
    }

    @RequestMapping("/addScanUrl")
    public Object addScanUrl(@RequestParam Map<String, Object> params) {
        String url = DataTransUtil.null2String(params.get("url"));
        if (StringUtils.isEmpty(url)) {
            return Result.fail("url is empty");
        }
        return Result.success(scanService.addScanUrl(url));
    }

    @RequestMapping("/delScanUrl")
    public Object delScanUrl(@RequestParam Map<String, Object> params) {
        String url = DataTransUtil.null2String(params.get("url"));
        if (StringUtils.isEmpty(url)) {
            return Result.fail("url is empty");
        }
        return Result.success(scanService.delScanUrl(url));
    }


    @RequestMapping("/confirmX")
    public Object confirmX(@RequestParam Map<String, Object> params) {
        String x = DataTransUtil.null2String(params.get("x"));
        if (StringUtils.isEmpty(x)) {
            return Result.fail("x is empty");
        }
        return Result.success(xAddressService.confirmX(x));
    }

    @RequestMapping("/confirmCa")
    public Object confirmCa(@RequestParam Map<String, Object> params) {
        String x = DataTransUtil.null2String(params.get("ca"));
        if (StringUtils.isEmpty(x)) {
            return Result.fail("ca is empty");
        }
        return Result.success(caService.confirmCa(x));
    }
}
