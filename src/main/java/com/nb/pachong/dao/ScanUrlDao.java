package com.nb.pachong.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ScanUrlDao {

    /**
     * getScanUrls
     *
     * @return
     */
    List<String> getScanUrls();

    /**
     * addUrl
     *
     * @param url
     * @param createTime
     * @return
     */
    boolean addUrl(@Param("url") String url, @Param("createTime") Date createTime);

    /**
     * deleteUrl
     *
     * @param url
     * @param updateTime
     * @return
     */
    boolean deleteUrl(@Param("url") String url, @Param("updateTime") Date updateTime);

    /**
     * update
     *
     * @param url
     * @param updateTime
     * @return
     */
    boolean update(@Param("url") String url, @Param("updateTime") Date updateTime);
}
