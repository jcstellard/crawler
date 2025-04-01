package com.nb.pachong.util;

/**
 * DataTransUtil
 */
public class DataTransUtil {

    public static String null2String(Object o) {
        return o == null ? "" : o.toString();
    }

    public static int getIntValue(String v) {
        return getIntValue(v, -1);
    }

    public static int getIntValue(String v, int def) {
        if (v == null || v.length() == 0) {
            return def;
        }
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return def;
        }
    }

    public static int getIntValue(Object o) {
        return getIntValue(null2String(o));
    }

    public static float getFloatValue(String v) {
        return getFloatValue(v, -1);
    }

    public static float getFloatValue(String v, float def) {
        if (v == null || v.length() == 0) {
            return def;
        }
        try {
            return Float.parseFloat(v);
        } catch (Exception ex) {
            return def;
        }
    }

    public static double getDoubleValue(String v) {
        return getDoubleValue(v, -1);
    }

    public static double getDoubleValue(String v, double def) {
        if (v == null || v.length() == 0) {
            return def;
        }
        try {
            return Double.parseDouble(v);
        } catch (Exception ex) {
            return def;
        }
    }

    public static long getLongValue(Object v) {
        return getLongValue(null2String(v), -1);
    }

    public static long getLongValue(String v) {
        return getLongValue(v, -1);
    }

    public static long getLongValue(String v, long def) {
        if (v == null || v.length() == 0) {
            return def;
        }
        try {
            return Long.parseLong(v);
        } catch (Exception ex) {
            return def;
        }
    }

    public static boolean getBooleanValue(Object v){
        return getBooleanValue(null2String(v),false);
    }

    public static boolean getBooleanValue(String v, boolean def){
        if (v == null || v.length() == 0){
            return def;
        }
        try{
            return Boolean.parseBoolean(v);
        } catch (Exception e){
            return def;
        }
    }

}
