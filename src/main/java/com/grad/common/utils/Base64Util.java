package com.grad.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * 描述 ：编码工具类
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 14:06
 **/
public class Base64Util {
    /**
     * 解密字符串
     **/
    public static String decodeBase64(String cryptStr) {
        byte[] bytes = Base64.decodeBase64(cryptStr);
        return new String(bytes);
    }

    /**
     * 加密字符串
     **/
    public static String encodeBase64(final String source) {
        byte b[] = source.getBytes();
        byte[] results = Base64.encodeBase64(b);
        return new String(results);
    }
}
