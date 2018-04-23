package com.grad.service;

import com.grad.common.utils.Base64Util;
import com.grad.common.eneity.QueryResultObject;
import com.grad.dao.ProjectManagerDao;
import com.grad.eneity.Project;
import com.grad.eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/19 15:02
 **/
@Service
public class ProjectManagerService {

    private Logger logger = LoggerFactory.getLogger(ProjectManagerService.class);

    @Autowired
    private ProjectManagerDao projectManagerDao;

    /**
     * 带查询的分页与不带查询的分页
     */
    public Map<String,Object> limitQuery(Integer pageNumber, Integer pageSize, String projectName) {
        Map<String, Object> map;
        if (projectName != null && !"".equals(projectName)) {
            map = this.fuzzyQueryPaging(pageNumber, pageSize,projectName);
        } else {
            map = this.initPaging(pageNumber, pageSize);
        }
        return map;
    }

    /**
     *  带名字的模糊查询
     */
    private Map<String,Object> fuzzyQueryPaging(Integer pageNumber, Integer pageSize, String projectName) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        List<Project> rows = projectManagerDao.fuzzyQueryPaging(pageNumber, pageSize, projectName);
        init(rows);
        //查询总记录数
        Integer total = projectManagerDao.findtotalByName(projectName);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 不带查询的分页
     */
    private Map<String,Object> initPaging(Integer pageNumber, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        List<Project> rows = projectManagerDao.initPaging(pageNumber, pageSize);
        //查询总记录数
        Integer total = projectManagerDao.total();
        init(rows);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }
    /**
     * 带查询的分页
     */
    private Map<String,Object> initQueryPaging(Integer pageNumber, Integer pageSize, String projectName) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        //查询总记录数
        Integer total = projectManagerDao.findtotalByName(projectName);
        //根据参数查询数据
        List<Project> rows = projectManagerDao.initQueryPaging(pageNumber,pageSize,projectName);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 校验后更新
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public QueryResultObject updateProjectService(Project project, HttpSession session) {
        QueryResultObject msg = new QueryResultObject();
        User nowUser = (User) session.getAttribute("user");
        if(nowUser == null){
            msg.setMsg("登录过期！请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //做更新操作Name不能为空
        if(project.getName() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作项目名为空!");
            return msg;
        }
        if(project.getNumber() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作项目编号为空!");
            return msg;
        }
        if(project.getManager() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作项目负责人为空!");
            return msg;
        }
        if(project.getNature() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作项目性质为空!");
            return msg;
        }
        if(project.getMember() ==null){
            msg.setResult(false);
            msg.setMsg("更新操作项目成员为空!");
            return msg;
        }
        if(nowUser.getPermission() != 1){
            msg.setResult(false);
            msg.setMsg("您无权审核项目！");
            return msg;
        }
        Project projectResult = projectManagerDao.findByNumber(project.getNumber());
        String checkResult = this.check(project);
        if ("错误".equals(checkResult)){
            msg.setResult(false);
            msg.setMsg("数据错误!");
            return msg;
        }
        try {
            if (projectResult == null){
                msg.setResult(false);
                msg.setMsg("不存在编号为 " + project.getNumber() + " 的数据!");
                return msg;
            }
            projectResult.setNumber(project.getNumber());
            projectResult.setName(project.getName());
            projectResult.setManager(project.getManager());
            projectResult.setMember(project.getMember());
            projectResult.setNature(project.getNature());
            projectResult.setDesc(project.getDesc());
            projectResult.setAwards(project.getAwards());
            projectResult.setStatus(project.getStatus());
            projectResult.setCreateUser(project.getCreateUser());
            //审核人为当前用户
            projectResult.setCheckUser(nowUser.getRealName());
            projectResult.setSavePath(project.getSavePath());
        }catch (Exception e){
            logger.error("项目编号创建后不可更改! 项目编号： " +project.getNumber() + e.toString());
            msg.setResult(false);
            msg.setMsg("项目编号创建后不可更改! 项目编号： " +project.getNumber());
            return msg;
        }
        try {
            this.updateProject(projectResult);
            msg.setResult(true);
            msg.setMsg("审核完成!");
            return msg;
        }catch (Exception e){
            logger.error("更新project表出错! 项目编号：" +project.getNumber() + e.toString());
            msg.setResult(false);
            msg.setMsg("更新项目出错! 项目编号：" +project.getNumber());
            return msg;
        }
    }

    /**
     * 更新项目操作
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void updateProject(Project projectResult) {
        projectManagerDao.updateProject(projectResult);
    }


    /**
     * 数据正确性校验
     */
    private String check(Project project) {
        if(project.getName() != null && project.getName().length() >100){
            return "错误";
        }
        if(project.getNumber() != null && project.getNumber() <0 ){
            return "错误";
        }
        if(project.getManager() != null && project.getManager().length() >100){
            return "错误";
        }
        if(project.getNature() != null && project.getNature().length() >100){
            return "错误";
        }
        return "正确";
    }

    public Map<String,Object> limitQueryByStatus(Integer pageNumber, Integer pageSize, Integer status) {
        Map<String, Object> map;
        if (status != null) {
            map = this.initQueryPagingStatus(pageNumber, pageSize,status);
        } else {
            map = this.initPaging(pageNumber, pageSize);
        }
        return map;
    }

    /**
     * 根据审核状态查询
     */
    public Map<String,Object> initQueryPagingStatus(Integer pageNumber, Integer pageSize, Integer status) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        //查询总记录数
        Integer total = projectManagerDao.findtotalByStatus(status);
        //根据参数查询数据
        List<Project> rows = projectManagerDao.initQueryPagingByStatus(pageNumber,pageSize,status);
        init(rows);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }


    /**
     * 初始化下载地址与审核人
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
            String checkUser = project.getCheckUser();
            String user;
            if (checkUser == null || "".equals(checkUser)) {
                user = "暂未审核";
            } else {
                if (0 == project.getStatus()) {
                    user = "暂未审核";
                } else {
                    user = "<b>" + checkUser + "</b>";
                }
            }
            project.setCheckUser(user);
        }
    }
}
