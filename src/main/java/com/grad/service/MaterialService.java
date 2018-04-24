package com.grad.service;

import com.grad.common.utils.Base64Util;
import com.grad.dao.MaterialDao;
import com.grad.eneity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：MaterialService
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 13:53
 **/
@Service
@Transactional(readOnly = true)
public class MaterialService {

    private static Logger logger = LoggerFactory.getLogger(MaterialService.class);

    @Resource
    private MaterialDao materialDao;

    /**
     * 带名字的模糊查询
     */
    public Map<String, Object> fuzzyQueryPaging(Integer pageNumber, Integer pageSize, String projectName) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        List<Project> rows = materialDao.fuzzyQueryPaging(pageNumber, pageSize, projectName);
        init(rows);
        //查询总记录数
        Integer total = materialDao.totalByName(projectName);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 初始化下载地址
     *
     * @param rows 下载结果集
     */
    public void init(List<Project> rows) {
        for (Project project : rows) {
            String str = project.getSavePath();
            String href;
            if (str == null || "".equals(str)) {
                href = "暂无材料";
            } else {
                String savePath = Base64Util.encodeBase64(str);
                href = "<a onclick=\"return js_download(this);\"" + " name=\"" + savePath + "\"" + ">下载</a >";
            }
            project.setSavePath(href);
        }
    }

    /**
     * 分页查询数据
     */
    public Map<String, Object> initPaging(Integer pageNumber, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        List<Project> rows = materialDao.initPaging(pageNumber, pageSize);
        init(rows);
        //查询总记录数
        Integer total = materialDao.total();
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 根据项目名模糊查询
     */
    public List<Project> findByNameLike(String name, Integer limit) {
        return materialDao.findByNameLike(name, limit);
    }

    /**
     * 根据名称查询创建者的
     */
    public Map<String, Object> queryByName(String name) {
        Map<String, Object> map = new HashMap<>(2);
        List<Project> rows = materialDao.findByName(name);
        String createUser = rows.get(0).getCreateUser();
        map.put("data", createUser);
        return map;
    }

    /**
     * 根据名称查询 返回list
     */
    public List<Project> findByName(String name) {
        return materialDao.findByName(name);
    }

    /**
     * 更新文件路径
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateSavePathById(Integer id, String savePath) {
        Project p = materialDao.findById(id);
        if (p != null) {
            p.setSavePath(savePath);
            materialDao.updateProject(p);
        }
    }

}
