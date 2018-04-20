package com.grad.dao;

import com.grad.eneity.SystemConfig;
import org.springframework.stereotype.Repository;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 16:25
 **/
@Repository
public interface SystemConfigDao {
    SystemConfig findConfigByName(String name);

    String findValueByName(String name);

    void updateConfigByName(SystemConfig systemConfig);
}
