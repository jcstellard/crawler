package com.nb.pachong.entity;

import java.util.Objects;

/**
 * url
 */
public class UrlEntity {

    private String url;

    private String title;

    private boolean isTuiTe;

    public UrlEntity(String url, String title, boolean isTuiTe)
    {
        this.url = url;
        this.title = title;
        this.isTuiTe = isTuiTe;
    }

    public UrlEntity() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTuiTe() {
        return isTuiTe;
    }

    public void setTuiTe(boolean tuiTe) {
        isTuiTe = tuiTe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlEntity urlEntity = (UrlEntity) o;
        return Objects.equals(url, urlEntity.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
