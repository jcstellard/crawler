package com.nb.pachong.service;

import com.nb.pachong.entity.XEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * XAddressService
 */
public interface XAddressService {

    /**
     * addXAddress
     *
     * @param xAddressRes
     * @param date
     * @return
     */
    int addXAddress(Map<String, Set<String>> xAddressRes, Date date);


    /**
     * getX
     *
     * @param params
     * @return
     */
    List<XEntity> getX(Map<String, Object> params);


    /**
     * getXByPage
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> getXByPage(Map<String, Object> params);

    /**
     * confirmX
     *
     * @param x
     * @return
     */
    boolean confirmX(String x);

    /**
     * send
     *
     * @param cas
     * @return
     */
    boolean send(List<String> cas);
}
