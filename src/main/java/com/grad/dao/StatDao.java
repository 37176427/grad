package com.grad.dao;

import org.springframework.stereotype.Repository;

/**
 * 描述 ：StatDao
 * 作者 ：WangYunHe
 * 时间 ：2018/4/24 10:49
 **/
@Repository
public interface StatDao {

    int statByMaterialYes();

    int statByMaterialNo();

    int statByChecked(int status);
}
