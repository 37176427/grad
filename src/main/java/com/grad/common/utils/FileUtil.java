package com.grad.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {

    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 单纯的创建文件夹 存在：不处理，不存在：创建
     * @param path 创建的文件夹路径
     * @return 创建结果
     */
    public static boolean mkDirs(String path) {
        // window路径替换
        if ("\\".equals(File.separator)) {
            path = path.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            path = path.replace("\\", "/");
        }
        boolean flag = false;
        File file = new File(path);
        // 不存在 创建
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            flag = true;
        }
        return flag;
    }
}

