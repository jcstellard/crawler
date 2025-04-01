package com.nb.pachong.service.impl;

import com.nb.pachong.dao.XDao;
import com.nb.pachong.entity.CaEntity;
import com.nb.pachong.entity.DateUtil;
import com.nb.pachong.entity.XEntity;
import com.nb.pachong.service.XAddressService;
import com.nb.pachong.util.DataTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class XAddressServiceImpl implements XAddressService {

    @Autowired
    private XDao xDao;

    @Override
    public int addXAddress(Map<String, Set<String>> xAddressRes, Date date) {
        if (xAddressRes == null) {
            return 0;
        }
        Set<Map.Entry<String, Set<String>>> entries = xAddressRes.entrySet();

        int res = 0;

        for (Map.Entry<String, Set<String>> entry : entries) {
            String x = entry.getKey();
            int count = xDao.findCount(x);
            if (count == 0) {
                boolean b = xDao.addX(x, date);
                if (b) {
                    res++;
                }
            } else {
                xDao.updateTime(x, date);
            }
            //查询该ca对应的url
            List<String> xUrls = xDao.getXUrls(x);

            Set<String> urls = entry.getValue();

            for (String url : urls) {
                if (!xUrls.contains(url)) {
                    xDao.addXUrl(x, url, date);
                } else {
                    xDao.updateXUrlTime(x, url, date);
                }
            }
        }

        return res;
    }

    /**
     * getX
     *
     * @param params
     * @return
     */
    @Override
    public List<XEntity> getX(Map<String, Object> params) {
        int send = DataTransUtil.getIntValue(params.get("send"));
        if (send != 0) {
            send = 1;
        }

        List<XEntity> ca = xDao.getX(send);

        for (XEntity caEntity : ca) {
            caEntity.setUrls(new HashSet<>(xDao.getXUrls(caEntity.getX_address())));
        }

        return ca;
    }

    /**
     * getXByPage
     *
     * @param params
     * @return
     */
    @Override
    public List<Map<String, Object>> getXByPage(Map<String, Object> params) {
        int send = DataTransUtil.getIntValue(params.get("send"));
        int page = DataTransUtil.getIntValue(DataTransUtil.null2String(params.get("page")), 1);
        int pageSize = DataTransUtil.getIntValue(DataTransUtil.null2String(params.get("pageSize")), 10);
        if (send != 0) {
            send = 1;
        }

        int num = (page - 1) * pageSize;
        List<XEntity> ca = xDao.getXByPage(send, num, pageSize);

        List<Map<String, Object>> caMap = new ArrayList<>(ca.size());
        for (XEntity caEntity : ca) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("x_address", caEntity.getX_address());
            map.put("create_time", DateUtil.getDateStr(caEntity.getCreate_time()));
            map.put("is_send", caEntity.getIs_send());

            HashSet<String> strings = new HashSet<>(xDao.getXUrls(caEntity.getX_address()));
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
     * confirmX
     *
     * @param x
     * @return
     */
    @Override
    public boolean confirmX(String x) {
        return xDao.confirm(x, new Date());
    }

    @Override
    public boolean send(List<String> cas) {
        return xDao.send(cas, new Date());
    }
}
