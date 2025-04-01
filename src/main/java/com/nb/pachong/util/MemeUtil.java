package com.nb.pachong.util;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MemeUtil
 */
@Component
public class MemeUtil {

    /**
     * getCa
     *
     * @param document
     * @return
     */
    public static Set<String> getCa(Document document) {
        String html = document.html();
        Set<String> caSet = new HashSet<>();
        //sol
        String regex = "\\b[0-9A-Za-z]{43,44}\\b";
        //bsc
//        String regex = "^0x[0-9a-fA-F]{40}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            caSet.add(matcher.group());
        }

        return caSet;
    }
}
