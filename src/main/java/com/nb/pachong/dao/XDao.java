package com.nb.pachong.dao;

import com.nb.pachong.entity.XEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface XDao {

    /**
     * addX
     *
     * @param x
     * @param createTime
     * @return
     */
    boolean addX(@Param("x") String x, @Param("createTime") Date createTime);

    /**
     * getX
     *
     * @param send
     * @return
     */
    List<XEntity> getX(@Param("send") int send);

    /**
     * getXByPage
     *
     * @param send
     * @return
     */
    List<XEntity> getXByPage(@Param("send") int send, @Param("num") int num, @Param("pageSize") int pageSize);

    /**
     * updateTime
     *
     * @param x
     * @param updateTime
     * @return
     */
    boolean updateTime(@Param("x") String x, @Param("updateTime") Date updateTime);

    /**
     * confirm
     *
     * @param x
     * @param updateTime
     * @return
     */
    boolean confirm(@Param("x") String x, @Param("updateTime") Date updateTime);

    /**
     * findCount
     *
     * @param x
     * @return
     */
    int findCount(@Param("x") String x);

    /**
     * getXUrls
     */
    List<String> getXUrls(@Param("x") String x);

    /**
     * addXUrl
     *
     * @param x
     * @param url
     * @param createTime
     * @return
     */
    boolean addXUrl(@Param("x") String x, @Param("url") String url, @Param("createTime") Date createTime);

    /**
     * updateXUrlTime
     *
     * @param x
     * @param updateTime
     * @return
     */
    boolean updateXUrlTime(@Param("x") String x, @Param("url") String url, @Param("updateTime") Date updateTime);

    /**
     * send
     *
     * @param xs
     * @param updateTime
     * @return
     */
    boolean send(@Param("xs") List<String> xs, @Param("updateTime") Date updateTime);
}
