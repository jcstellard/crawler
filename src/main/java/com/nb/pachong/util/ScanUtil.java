package com.nb.pachong.util;

import com.alibaba.fastjson.JSON;
import com.nb.pachong.context.MainContext;
import com.nb.pachong.entity.ScanResultEntity;
import com.nb.pachong.entity.UrlEntity;
import org.jsoup.nodes.Document;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * ScanUtil
 */
public class ScanUtil {

    /**
     * scan url
     *
     * @param url
     * @return
     */
    public static ScanResultEntity scan(String url, CountDownLatch mainCountDownLatch) {
        try {
            ScanResultEntity scanResultEntity = new ScanResultEntity();
            scanResultEntity.setUrl(url);
            scanResultEntity.setDate(new Date());

            String mainThreadKey = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
            scanResultEntity.setMainThreadKey(mainThreadKey);


            Set<String> scanUrl = new HashSet<>();
            MainContext.currentThreadScanUrl.put(mainThreadKey, scanUrl);

            Map<String, Set<UrlEntity>> result = new ConcurrentHashMap<>();
            scanResultEntity.setCaRes(result);

            Map<String, Set<String>> tuiTeUrls = new ConcurrentHashMap<>();
            scanResultEntity.setxAddressRes(tuiTeUrls);

            UrlEntity urlEntity = new UrlEntity(url, "", url.contains("https://x.com"));

            CountDownLatch countDownLatch = new CountDownLatch(1);
            ThreadPoolUtil.getOneThreadPool().execute(() -> {
                scan(urlEntity, mainThreadKey, result, countDownLatch, tuiTeUrls);
            });

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("------------> main url：" + url + ",scan url：" + JSON.toJSONString(scanUrl));

            MainContext.currentThreadScanUrl.remove(mainThreadKey);

            return scanResultEntity;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mainCountDownLatch.countDown();
        }
        return null;
    }


    /**
     * scan
     *
     * @param urlEntity
     * @param mainThreadKey
     * @param result
     */
    public static void scan(UrlEntity urlEntity, String mainThreadKey, Map<String, Set<UrlEntity>> result, CountDownLatch countDownLatch, Map<String, Set<String>> tuiTeUrls) {
        try {
            Set<String> scanUrl = MainContext.currentThreadScanUrl.get(mainThreadKey);
            String url = urlEntity.getUrl();

            if (scanUrl.contains(url)) {
                return;
            }

            scanUrl.add(url);

            Document document = JsoupUtil.getDocument(url);
            if (document == null) {
                return;
            }

//            if (url.contains("api") || url.contains("docs") || url.contains("https://x.com/") || url.contains("github")){
                Set<String> cas = MemeUtil.getCa(document);
                for (String ca : cas) {
                    Set<UrlEntity> urlEntities = result.get(ca);
                    if (urlEntities == null) {
                        urlEntities = new HashSet<>();
                        result.put(ca, urlEntities);
                    }
                    urlEntities.add(new UrlEntity(url, urlEntity.getTitle(), urlEntity.isTuiTe()));
                }
//            }

            Set<UrlEntity> urls = HrefUtil.getHref(document, url);
            if (!CollectionUtils.isEmpty(urls)) {
                CountDownLatch myDownLatch = new CountDownLatch(urls.size());
                for (UrlEntity entity : urls) {
                    if (entity.isTuiTe()) {
                        if (!"https://twitter.com/privacy".equals(url) && !"https://twitter.com/tos".equals(url)) {
                            if (!tuiTeUrls.containsKey(entity.getUrl())) {
                                Set<String> tuiTeUrl = new HashSet<>();
                                tuiTeUrl.add(url);
                                tuiTeUrls.put(entity.getUrl(), tuiTeUrl);
                            } else {
                                Set<String> tuiTeUrl = tuiTeUrls.get(entity.getUrl());
                                tuiTeUrl.add(url);
                            }
                        }
                    }
                    ThreadPoolUtil.getOneThreadPool().execute(() -> {
                        scan2(entity, mainThreadKey, result, myDownLatch, tuiTeUrls);
                    });
                }

                myDownLatch.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    public static void scan2(UrlEntity urlEntity, String mainThreadKey, Map<String, Set<UrlEntity>> result, CountDownLatch countDownLatch, Map<String, Set<String>> tuiTeUrls) {
        try {
            Set<String> scanUrl = MainContext.currentThreadScanUrl.get(mainThreadKey);
            String url = urlEntity.getUrl();

            if (scanUrl.contains(url)) {
                return;
            }

            scanUrl.add(url);

            Document document = JsoupUtil.getDocument(url);
            if (document == null) {
                return;
            }

//            if (url.contains("api") || url.contains("docs")){
                Set<String> cas = MemeUtil.getCa(document);
                for (String ca : cas) {
                    Set<UrlEntity> urlEntities = result.get(ca);
                    if (urlEntities == null) {
                        urlEntities = new HashSet<>();
                        result.put(ca, urlEntities);
                    }
                    urlEntities.add(new UrlEntity(url, urlEntity.getTitle(), urlEntity.isTuiTe()));
                }
//            }

            Set<UrlEntity> urls = HrefUtil.getHref(document, url);
            if (!CollectionUtils.isEmpty(urls)) {
                CountDownLatch myDownLatch = new CountDownLatch(urls.size());
                for (UrlEntity entity : urls) {
                    if (entity.isTuiTe()) {
                        if (!"https://twitter.com/privacy".equals(url) && !"https://twitter.com/tos".equals(url)) {
                            if (!tuiTeUrls.containsKey(entity.getUrl())) {
                                Set<String> tuiTeUrl = new HashSet<>();
                                tuiTeUrl.add(url);
                                tuiTeUrls.put(entity.getUrl(), tuiTeUrl);
                            } else {
                                Set<String> tuiTeUrl = tuiTeUrls.get(entity.getUrl());
                                tuiTeUrl.add(url);
                            }
                        }
                    }
                    ThreadPoolUtil.getTwoThreadPool().execute(() -> {
                        scan3(entity, mainThreadKey, result, myDownLatch, tuiTeUrls);
                    });
                }

                myDownLatch.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    public static void scan3(UrlEntity urlEntity, String mainThreadKey, Map<String, Set<UrlEntity>> result, CountDownLatch countDownLatch, Map<String, Set<String>> tuiTeUrls) {
        try {
            Set<String> scanUrl = MainContext.currentThreadScanUrl.get(mainThreadKey);
            String url = urlEntity.getUrl();

            if (scanUrl.contains(url)) {
                return;
            }

            scanUrl.add(url);

            Document document = JsoupUtil.getDocument(url);
            if (document == null) {
                return;
            }

            //get ca
//            if (url.contains("api") || url.contains("docs")){
                Set<String> cas = MemeUtil.getCa(document);
                for (String ca : cas) {
                    Set<UrlEntity> urlEntities = result.get(ca);
                    if (urlEntities == null) {
                        urlEntities = new HashSet<>();
                        result.put(ca, urlEntities);
                    }
                    urlEntities.add(new UrlEntity(url, urlEntity.getTitle(), urlEntity.isTuiTe()));
                }
//            }

            //get url
            Set<UrlEntity> urls = HrefUtil.getHref(document, url);
            if (!CollectionUtils.isEmpty(urls)) {
                for (UrlEntity entity : urls) {
                    if (entity.isTuiTe()) {
                        if (!"https://twitter.com/privacy".equals(url) && !"https://twitter.com/tos".equals(url)) {
                            if (!tuiTeUrls.containsKey(entity.getUrl())) {
                                Set<String> tuiTeUrl = new HashSet<>();
                                tuiTeUrl.add(url);
                                tuiTeUrls.put(entity.getUrl(), tuiTeUrl);
                            } else {
                                Set<String> tuiTeUrl = tuiTeUrls.get(entity.getUrl());
                                tuiTeUrl.add(url);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

}
