package com.grad.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 14:06
 **/
public class Base64Util {
    /**
     * 解密字符串
     **/
    public static String decodeBase64(String cryptStr){
        byte[] bytes = Base64.decodeBase64(cryptStr);
        return new String(bytes);
    }

    /**
     * 加密字符串
     * */
    public static String encodeBase64(final String source){
        byte b[] = source.getBytes();
        byte[] results = Base64.encodeBase64(b);
        return new String(results);
    }

    public static String encodeFile(String filePath) throws IOException {
        InputStream reader = Base64Util.class.getResourceAsStream(filePath);
        byte b[] = IOUtils.toByteArray(reader);
        byte[] results = Base64.encodeBase64(b);
        return new String(results);
    }
}
