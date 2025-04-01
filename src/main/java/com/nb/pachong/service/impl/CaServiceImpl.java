package com.nb.pachong.service.impl;

import com.nb.pachong.dao.CaDao;
import com.nb.pachong.entity.CaEntity;
import com.nb.pachong.entity.DateUtil;
import com.nb.pachong.entity.UrlEntity;
import com.nb.pachong.service.CaService;
import com.nb.pachong.util.DataTransUtil;
import com.nb.pachong.util.MemeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CaServiceImpl
 */
@Service
public class CaServiceImpl implements CaService {

    @Autowired
    private CaDao caDao;

    @Autowired
    private MemeUtil memeUtil;

    /**
     * addCa
     *
     * @param caRes
     * @param date
     * @return
     */
    @Override
    public int addCa(Map<String, Set<UrlEntity>> caRes, Date date) {
        if (caRes == null) {
            return 0;
        }
        Set<Map.Entry<String, Set<UrlEntity>>> entries = caRes.entrySet();

        int res = 0;

        for (Map.Entry<String, Set<UrlEntity>> entry : entries) {
            String ca = entry.getKey();
            int count = caDao.findCount(ca);
            if (count == 0) {
                boolean b = caDao.addUrl(ca, date);
                if (b) {
                    res++;
                }
            } else {
                caDao.updateTime(ca, date);
            }
            List<String> caUrls = caDao.getCaUrls(ca);

            Set<UrlEntity> urls = entry.getValue();
            List<String> urlsList = urls.stream().map(UrlEntity::getUrl).distinct().collect(Collectors.toList());

            for (String url : urlsList) {
                if (!caUrls.contains(url)) {
                    caDao.addCaUrl(ca, url, date);
                } else {
                    caDao.updateCaUrlTime(ca, url, date);
                }
            }
        }

        return res;
    }

    @Override
    public List<CaEntity> getCa(Map<String, Object> params) {
        int send = DataTransUtil.getIntValue(params.get("send"));
        if (send != 0) {
            send = 1;
        }
        boolean pump = "1".equals(DataTransUtil.null2String(params.get("pump")));

        List<CaEntity> ca = caDao.getCa(send, pump);


        Iterator<CaEntity> iterator = ca.iterator();
        while (iterator.hasNext()) {

            CaEntity caEntity = iterator.next();

//            Object memeInfo = memeUtil.getMemeInfo(caEntity.getCa());

            List<String> caUrls = caDao.getCaUrls(caEntity.getCa());
            if (caUrls.size() == 1) {
                String s = caUrls.get(0);
                if (s.startsWith("https://jup.ag") || s.startsWith("https://birdeye.so/") || s.startsWith("https://web3.bitget.com/en/swap")){
                    iterator.remove();
                }
                if ("https://coinmarketcap.com/zh/?utm_source=BitgetWallet&source=BitgetWallet".equals(caUrls.get(0))
                        | "https://web3.bitget.com/en/swap".equals(caUrls.get(0))) {
                    iterator.remove();
                }
            } else if (caUrls.size()>1) {
                String s = caUrls.get(0);
                if (s.startsWith("https://jup.ag") || s.startsWith("https://birdeye.so/") || s.startsWith("https://web3.bitget.com/en/swap")){
                    iterator.remove();
                }
            }


            caEntity.setUrls(new HashSet<>(caUrls));

        }


        return ca;
    }

    @Override
    public List<Map<String, Object>> getCaByPage(Map<String, Object> params) {
        int send = DataTransUtil.getIntValue(params.get("send"));
        int page = DataTransUtil.getIntValue(DataTransUtil.null2String(params.get("page")), 1);
        int pageSize = DataTransUtil.getIntValue(DataTransUtil.null2String(params.get("pageSize")), 5);
        if (send != 0) {
            send = 1;
        }

        int num = (page - 1) * pageSize;
        boolean pump = "1".equals(DataTransUtil.null2String(params.get("pump")));
        List<CaEntity> ca = caDao.getCaByPage(send, num, pageSize, pump);
        List<Map<String, Object>> caMap = new ArrayList<>(ca.size());
        for (CaEntity caEntity : ca) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("ca", caEntity.getCa());
            map.put("create_time", DateUtil.getDateStr(caEntity.getCreate_time()));
            map.put("is_send", caEntity.getIs_send());

            HashSet<String> strings = new HashSet<>(caDao.getCaUrls(caEntity.getCa()));
            StringJoiner stringJoiner = new StringJoiner("</br>");
            for (String string : strings) {
                stringJoiner.add(string);
            }
            map.put("urls", stringJoiner.toString());

            caMap.add(map);
        }

        return caMap;
    }

    /**
     * confirmCa
     *
     * @param ca
     * @return
     */
    @Override
    public boolean confirmCa(String ca) {
        return caDao.confirm(ca, new Date());
    }

    @Override
    public boolean send(List<String> cas) {
        return caDao.send(cas, new Date());
    }
}
