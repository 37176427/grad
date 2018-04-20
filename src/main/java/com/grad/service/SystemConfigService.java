package com.grad.service;

import com.grad.dao.SystemConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 16:24
 **/
@Service
public class SystemConfigService {
    @Autowired
    private SystemConfigDao systemConfigDao;

    public String getPath(String param) {
        String dirPath = systemConfigDao.findValueByName(param);
        if (StringUtils.isEmpty(dirPath)) {
            return "";
        }
        return dirPath;
    }
}
