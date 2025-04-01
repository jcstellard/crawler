package com.nb.pachong.service;

import com.nb.pachong.entity.CaEntity;
import com.nb.pachong.entity.UrlEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CaService
 */
public interface CaService {

    /**
     * addCa
     *
     * @param caRes
     * @param date
     * @return
     */
    int addCa(Map<String, Set<UrlEntity>> caRes, Date date);

    /**
     * getCa
     *
     * @param params
     * @return
     */
    List<CaEntity> getCa(Map<String, Object> params);

    /**
     * getCaByPage
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> getCaByPage(Map<String, Object> params);

    /**
     * confirmCa
     *
     * @param ca
     * @return
     */
    boolean confirmCa(String ca);

    /**
     * send
     *
     * @param cas
     * @return
     */
    boolean send(List<String> cas);
}
