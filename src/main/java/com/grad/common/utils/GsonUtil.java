package com.grad.common.utils;

import com.google.gson.Gson;

/**
 * 描述 ：Gson工具类
 * 作者 ：WangYunHe
 * 时间 ：2018/4/24 10:52
 **/
public class GsonUtil {

    private static Gson gson = new Gson();

    /**
     * 将一个对象解析成json字符串
     **/
    public static String getJsonStringByObject(Object obj) {
        return gson.toJson(obj);
    }

}
