package com.nb.pachong.context;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MainContext {

    public static Map<String, Set<String>> currentThreadScanUrl = new ConcurrentHashMap<>();

}
