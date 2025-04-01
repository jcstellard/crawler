package com.nb.pachong.service;

import com.nb.pachong.entity.XEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 推特地址Service
 */
public interface XAddressService {

    /**
     * 添加推特地址
     *
     * @param xAddressRes
     * @param date
     * @return
     */
    int addXAddress(Map<String, Set<String>> xAddressRes, Date date);


    /**
     * 获取x
     *
     * @param params
     * @return
     */
    List<XEntity> getX(Map<String, Object> params);


    /**
     * 分页获取x
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> getXByPage(Map<String, Object> params);

    /**
     * 确认x
     *
     * @param x
     * @return
     */
    boolean confirmX(String x);

    /**
     * 更新发送状态
     *
     * @param cas
     * @return
     */
    boolean send(List<String> cas);
}
