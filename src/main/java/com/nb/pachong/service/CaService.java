package com.nb.pachong.service;

import com.nb.pachong.entity.CaEntity;
import com.nb.pachong.entity.UrlEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 合约地址Service
 */
public interface CaService {

    /**
     * 添加ca
     *
     * @param caRes
     * @param date
     * @return
     */
    int addCa(Map<String, Set<UrlEntity>> caRes, Date date);

    /**
     * 获取ca
     *
     * @param params
     * @return
     */
    List<CaEntity> getCa(Map<String, Object> params);

    /**
     * 分页获取ca
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> getCaByPage(Map<String, Object> params);

    /**
     * 确认ca
     *
     * @param ca
     * @return
     */
    boolean confirmCa(String ca);

    /**
     * 更新发送状态
     *
     * @param cas
     * @return
     */
    boolean send(List<String> cas);
}
