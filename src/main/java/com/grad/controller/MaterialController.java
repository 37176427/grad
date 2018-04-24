package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.common.utils.Base64Util;
import com.grad.common.utils.Constants;
import com.grad.common.utils.FileUtil;
import com.grad.eneity.Project;
import com.grad.eneity.User;
import com.grad.service.MaterialService;
import com.grad.service.SystemConfigService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：材料controller
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 13:52
 **/
@Controller
@RequestMapping("/material")
public class MaterialController {

    private static Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Resource
    private MaterialService ms;

    @Resource
    private SystemConfigService configService;

    /**
     * 初始化表格
     */
    @RequestMapping("/initPaging")
    @ResponseBody
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, String projectName) {
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = limitQuery(pageNumber, pageSize, projectName);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }

    /**
     * 带查询与不带查询的
     */
    private Map<String, Object> limitQuery(Integer pageNumber, Integer pageSize, String projectName) {
        Map<String, Object> map;
        if (projectName != null) {
            map = ms.fuzzyQueryPaging(pageNumber, pageSize, projectName);
        } else {
            map = ms.initPaging(pageNumber, pageSize);
        }
        return map;
    }

    /**
     * 根据工具名称实时模糊查询
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<String> queryNameLike(String name) {
        List<String> list = new ArrayList<>(5);
        List<Project> managerList = ms.findByNameLike(name, 5);
        for (Project project : managerList) {
            list.add(project.getName());
        }
        return list;
    }

    @RequestMapping("queryByName")
    @ResponseBody
    public QueryResultObject queryByName(String name) {
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = ms.queryByName(name);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }

    /**
     * 上传资料
     */
    @RequestMapping("/upload")
    @ResponseBody
    public QueryResultObject upload(Integer id, String name, String createUser, MultipartFile ctFile, HttpSession session) {
        QueryResultObject resultObject = new QueryResultObject();
        User nowUser = (User) session.getAttribute("user");
        if (nowUser == null) {
            resultObject.setMsg("登录过期！请重新登录！");
            resultObject.setResult(false);
            return resultObject;
        }
        //判断上传者与创建者是否一致
        if (!nowUser.getRealName().equals(createUser)) {
            resultObject.setMsg("您不是该项目的创建者，不能上传！");
            resultObject.setResult(false);
            return resultObject;
        }
        //校验名称
        QueryResultObject object = checkParam(name, 100);
        if (!object.isResult()) {
            return object;
        }
        //查询工具编号
        List<Project> managerList = ms.findByName(name);
        if (managerList.size() <= 0) {
            object.setMsg("请检查该项目名是否存在");
            object.setResult(false);
            return object;
        }
        String fileName = ctFile.getOriginalFilename();
        String rootPath = configService.getPath(Constants.UPLOAD_CONFIG_DIR);
        //文件保存路径
        String savePath = rootPath + id + Constants.DIR_SEPARATOR + fileName;
        //创建文件夹
        FileUtil.mkDirs(savePath.substring(0, savePath.lastIndexOf("/")));
        File file = new File(savePath);
        if (file.exists()) {
            FileUtils.deleteQuietly(file);
        }
        try {
            boolean newFile = file.createNewFile();
            if (newFile) {
                logger.info("创建文件:" + fileName + "成功");
            }
            InputStream inputStream = ctFile.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            //上传成功 更新project记录
            ms.updateSavePathById(id, savePath);
            resultObject.setResult(true);
            resultObject.setMsg("ID:" + id + "的项目材料上传成功,文件路径：" + savePath);
            logger.info("ID:" + id + "的项目材料上传成功,文件路径：" + savePath);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            logger.error("创建文件异常：" + e.getMessage());
            resultObject.setMsg("文件上传失败，内部发生错误，请稍后重试！！！");
            resultObject.setResult(false);
        }
        return resultObject;
    }


    /**
     * 校验数据长度
     *
     * @param param  参数
     * @param length 数据长度
     * @return 结果
     */
    private QueryResultObject checkParam(String param, Integer length) {
        String msg = "请检查输入数据的长度";
        QueryResultObject resultObject = new QueryResultObject();
        if ("".equals(param) || param == null) {
            msg = "请输入数据";
            resultObject.setMsg(msg);
            resultObject.setResult(false);
            return resultObject;
        }
        if (param.length() > length) {
            msg = msg + ",长度不得超过：" + length;
            resultObject.setMsg(msg + ",长度不得超过：" + length);
            resultObject.setResult(false);
            return resultObject;
        }
        resultObject.setResult(true);
        return resultObject;
    }

    /**
     * 下载文件前检查
     */
    @GetMapping(value = "/checkFile")
    @ResponseBody
    public QueryResultObject checkFile(String url) {
        QueryResultObject object = new QueryResultObject();
        //先解码
        String filePath = Base64Util.decodeBase64(url);
        File file = new File(filePath);
        if (file.exists()) {
            object.setResult(true);
        } else {
            object.setMsg("您下载的文件不存在或已经删除");
            object.setResult(false);
        }
        return object;
    }

    /**
     * 下载文件
     */
    @GetMapping(value = "/download")
    @ResponseBody
    public void download(String url, HttpServletResponse response) throws Exception {
        //先解码
        String filePath = Base64Util.decodeBase64(url);
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        byte[] byteArray = IOUtils.toByteArray(inputStream);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.write(byteArray, outputStream);
    }
}
