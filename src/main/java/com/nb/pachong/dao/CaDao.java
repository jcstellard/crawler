package com.nb.pachong.dao;

import com.nb.pachong.entity.CaEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CaDao {

    /**
     * add ca
     *
     * @param ca
     * @param createTime
     * @return
     */
    boolean addUrl(@Param("ca") String ca, @Param("createTime") Date createTime);

    /**
     * get ca
     *
     * @param send
     * @return
     */
    List<CaEntity> getCa(@Param("send") int send, @Param("pump") boolean pump);

    /**
     * getCaByPage
     *
     * @param send
     * @return
     */
    List<CaEntity> getCaByPage(@Param("send") int send, @Param("num") int num, @Param("pageSize") int pageSize,@Param("pump") boolean pump);

    /**
     * updateTime
     *
     * @param ca
     * @param updateTime
     * @return
     */
    boolean updateTime(@Param("ca") String ca, @Param("updateTime") Date updateTime);

    /**
     * confirm
     *
     * @param ca
     * @param updateTime
     * @return
     */
    boolean confirm(@Param("ca") String ca, @Param("updateTime") Date updateTime);

    /**
     * findCount
     *
     * @param ca
     * @return
     */
    int findCount(@Param("ca") String ca);

    /**
     * getCaUrls
     */
    List<String> getCaUrls(@Param("ca") String ca);

    /**
     * addCaUrl
     *
     * @param ca
     * @param url
     * @param createTime
     * @return
     */
    boolean addCaUrl(@Param("ca") String ca, @Param("url") String url, @Param("createTime") Date createTime);

    /**
     * updateCaUrlTime
     *
     * @param ca
     * @param updateTime
     * @return
     */
    boolean updateCaUrlTime(@Param("ca") String ca, @Param("url") String url, @Param("updateTime") Date updateTime);

    /**
     * send
     *
     * @param cas
     * @param updateTime
     * @return
     */
    boolean send(@Param("cas") List<String> cas, @Param("updateTime") Date updateTime);
}
