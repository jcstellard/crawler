package com.nb.pachong.util;

import com.nb.pachong.entity.UrlEntity;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.Set;

public class HrefUtil {

    /**
     * getHref
     *
     * @param document
     * @param orgUrl
     * @return
     */
    public static Set<UrlEntity> getHref(Document document, String orgUrl) {
        Set<UrlEntity> urls = new HashSet<>();

        Elements links = document.select("a[href]");

        for (Element link : links) {
            UrlEntity urlEntity = new UrlEntity();

            urlEntity.setTitle(link.text());

            String href = link.attr("href");
            String url = null;
            if (href.startsWith("http")) {
                url = href;
            } else {
                String substring = orgUrl.substring(0, orgUrl.lastIndexOf("/"));
                url = substring + href;
            }
            urlEntity.setUrl(url);

            if (href.contains("https://x.com")){
                urlEntity.setTuiTe(true);
            }

//            if (url.startsWith("https://mintlify.com/security/responsible-disclosure")){
//                continue;
//            }
//
//            if (url.startsWith("https://jup.ag/")){
//                continue;
//            }
//
//            if (url.startsWith("https://birdeye.so/")){
//                continue;
//            }
//
//            if (url.startsWith("https://web3.bitget.com/en/swap")){
//                continue;
//            }
//
//            if (url.startsWith("https://coinmarketcap.com/zh/?utm_source=BitgetWallet&source=BitgetWallet")){
//                continue;
//            }
//
//            if (url.startsWith("https://solscan.io")){
//                continue;
//            }
//
//            if (url.startsWith("https://solana.com/zh/news")){
//                continue;
//            }
//
//            if (url.startsWith("https://app.sanctum.so/")){
//                continue;
//            }
//
//            if (url.startsWith("https://jup.ag")){
//                continue;
//            }
//
//            if (url.startsWith("https://birdeye.so/") || url.startsWith("https://web3.bitget.com/en/swap")){
//                continue;
//            }
//
//            if (url.startsWith("https://coinmarketcap.com/zh/?utm_source=BitgetWallet&source=BitgetWallet")){
//                continue;
//            }

            urls.add(urlEntity);
        }

        return urls;
    }
}
