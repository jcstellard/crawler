package com.nb.pachong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**
 * ScanResultEntity
 */
public class ScanResultEntity {

    private String url;

    @JsonIgnore
    private Date date;

    @JsonIgnore
    private String mainThreadKey;

    /**
     * key->ca
     * value->url
     */
    private Map<String, Set<UrlEntity>> caRes;

    /**
     * key->x url
     * value->url
     */
    @JsonIgnore
    private Map<String, Set<String>> xAddressRes;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMainThreadKey() {
        return mainThreadKey;
    }

    public void setMainThreadKey(String mainThreadKey) {
        this.mainThreadKey = mainThreadKey;
    }

    public Map<String, Set<UrlEntity>> getCaRes() {
        return caRes;
    }

    public void setCaRes(Map<String, Set<UrlEntity>> caRes) {
        this.caRes = caRes;
    }

    public Map<String, Set<String>> getxAddressRes() {
        return xAddressRes;
    }

    public void setxAddressRes(Map<String, Set<String>> xAddressRes) {
        this.xAddressRes = xAddressRes;
    }
}
