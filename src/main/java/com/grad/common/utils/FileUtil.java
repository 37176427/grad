package com.grad.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.List;


public class FileUtil {
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 文件是否存在
     * @param filePath
     * @return
     */
    public static boolean fileExists(String filePath) {

        filePath = filePath.replace("\\/", "/");
        filePath = filePath.replace("/\\", "/");
        // window路径替换
        if ("\\".equals(File.separator)) {
            filePath = filePath.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            filePath = filePath.replace("\\", "/");
        }
        File file = new File(filePath);
        return file.exists();
    }

    public static void buildAndClearPath(String dirPath) {
        File file = new File(dirPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File one : files) {
                    one.delete();
                }
            }
        } else {
            file.mkdirs();
        }

    }
    /**
     * 根据路径全名，获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.getName();
        } else {
            return null;
        }
    }

    /**
     * 获取文件父目录
     *
     * @param filePath
     * @return
     */
    public static String getFileParentPath(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.getParent();
        } else {
            return null;
        }
    }

    /**
     * 文件是否存在
     *
     * @param filePath
     *            全路径
     * @return
     */
    public static boolean existsFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    // 复制文件
    public static void copyFile(String srcFile, String destFile) {
        FileInputStream in = null;
        FileOutputStream out = null;
        FileChannel inc = null;
        FileChannel outc = null;
        srcFile = srcFile.replace("\\/", "/");
        srcFile = srcFile.replace("/\\", "/");
        destFile = destFile.replace("\\/\\", "/");
        destFile = destFile.replace("/\\", "/");
        // window路径替换
        if ("\\".equals(File.separator)) {
            srcFile = srcFile.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            srcFile = srcFile.replace("\\", "/");
        }
        // window路径替换
        if ("\\".equals(File.separator)) {
            destFile = destFile.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            destFile = destFile.replace("\\", "/");
        }
        try {
            System.out.println("123srcFile:" + srcFile);
            System.out.println("123destFile:" + destFile);
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            inc = in.getChannel();// 得到对应的文件通道
            outc = out.getChannel();// 得到对应的文件通道
            inc.transferTo(0, inc.size(), outc);// 连接两个通道，并且从in通道读取，然后写入out通道

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                inc.close();
                out.close();
                outc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        String FileExtension = "";
        if (fileName.lastIndexOf(".") != -1) {

            FileExtension = fileName.substring(fileName.lastIndexOf("."));

        }
        return FileExtension;
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                copyFile(sourceFile.getPath(), targetFile.getPath());
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath
     *            要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        // window路径替换
        if ("\\".equals(File.separator)) {
            sPath = sPath.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            sPath = sPath.replace("\\", "/");
        }
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath
     *            被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个FileOutputStream目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建目录，并要进行删除以前的文件
     *
     * @param path
     * @return
     */
    public static boolean mkDir(String path) {
        // window路径替换
        if ("\\".equals(File.separator)) {
            path = path.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            path = path.replace("\\", "/");
        }
        boolean falg = false;
        File file = new File(path);
        if (!file.exists()) { // 不存在创建
            file.mkdirs();
            falg = true;
        } else {// 存在删除
            deleteDirectory(file.getPath());
        }
        return falg;
    }

    /**
     * 只是纯粹的创建文件
     *
     * @param path
     * @return
     */
    public static boolean mkDirTwo(String path) {
        // window路径替换
        if ("\\".equals(File.separator)) {
            path = path.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            path = path.replace("\\", "/");
        }
        boolean falg = false;
        File file = new File(path);
        if (!file.exists()) { // 不存在创建
            file.mkdirs();
            falg = true;
        }
        return falg;
    }

    /**
     * 获取根目录
     *
     * @param filePath
     * @return
     */
    public static String getParentPath(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.getParent();
        }
        return null;
    }

    public static String filePath(String path) {
        // window路径替换
        if ("\\".equals(File.separator)) {
            path = path.replace("/", "\\");
        }
        // liunx下路径替换
        if ("/".equals(File.separator)) {
            path = path.replace("\\", "/");
        }
        return path;
    }

    public static void findFile(String filePath, List<File> filesList) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        } else {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    findFile(f.getPath(), filesList); // 如果是目录
                } else {
                    filesList.add(f);
                }

            }
        }
        return;
    }

    /**
     * 按默认字符集读取文件到字符串中
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readFileToString(String fileName) throws IOException {

        return FileUtil.readFileToString(fileName, Charset.defaultCharset().name());

    }

    /**
     * 将文件中的内容读进某个字符串
     *
     * @param fileName
     * @param charset
     * @return
     * @throws IOException
     * @author Franklin 2008-7-22
     */
    public static String readFileToString(String fileName, String charset) throws IOException {
        Charset cs = Charset.forName(charset);
        return readFileToString(fileName, cs);
    }

    /**
     * 将文件中的内容读进某个字符串
     *
     * @param fileName
     * @param cs
     * @return
     * @throws IOException
     * @author Franklin 2008-7-22
     */
    public static String readFileToString(String fileName, Charset cs) throws IOException {

        if (fileName == null)
            throw new IOException("fileName must not be null! ::: ");
        File f = new File(fileName);
        StringBuffer buffer = new StringBuffer();
        if (f.exists()) {
            FileChannel fc = new FileInputStream(f).getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024 * 3);

            while (fc.read(buff) != -1) {
                buff.flip();
                buffer.append(cs.decode(buff));
                buff.clear();
            }
            fc.close();
            return buffer.toString();
        }
        return null;

    }

    /**
     * @Title: checkPath
     * @Description: 检查一个目录，扫描目录下的所有文件，如果目录不存在则创建,如果存在，检查目录文件类型
     * @param path
     * @throws
     */
    public static void checkDirAndFile(String path, List<File> lists){
        File file = new File(path);
        if(!file.exists()){
            logger.warn("存放网站目录不存在，创建目录！" );
            file.mkdirs();
            logger.warn("创建目录完成！" );
            return;
        }else{
            File[] files = file.listFiles();
            for(File f: files){
                if(f.isDirectory()){
                    checkDirAndFile(f.getPath(), lists);
                }else {
                    String fileName = f.getName();
                    if( !fileName.contains(Constants.BAK_SUFFIX)){
                        lists.add(f);
                    }
                }
            }
        }
    }

    /**
     * @Title: renameFile
     * @Description: 重命名文件
     * @param file
     * @param flag 正常结束标记.bak，异常结束标记_error
     * @throws
     */
    public static void renameFile(File file, boolean flag){
        String oldName = file.getName();
        String path = file.getParent();
        String newName = oldName + Constants.BAK_SUFFIX;

        if(!flag)
            newName += Constants.ERROR_SUFFIX;

        if(!oldName.equals(newName)){
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);

            if(!oldFile.exists()){
                logger.warn("重命名失败，重命名文件不存在！" );
                return;
            }

            if(newFile.exists()){
                logger.warn("重命名失败，新文件名称已存在！" );
            }else{
                oldFile.renameTo(newFile);
                logger.warn("重命名成功，" + oldName + "已更新为[" + newName +"]" );
            }
        }else{
            logger.warn("重命名失败，新文件名称和就文件名称相同！" );
        }
    }

    /**
     *  单纯的创建文件夹 存在：不处理，不存在：创建
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
        // 不存在创建
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            flag = true;
        } else {
        }
        return flag;
    }
}

