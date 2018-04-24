package com.grad.dao;

import org.springframework.stereotype.Repository;

/**
 * 描述 ：SystemConfigDao
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 16:25
 **/
@Repository
public interface SystemConfigDao {

    String findValueByName(String name);

}
