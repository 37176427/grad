package com.grad.service;

import com.grad.common.utils.Base64Util;
import com.grad.common.eneity.QueryResultObject;
import com.grad.dao.ProjectDao;
import com.grad.eneity.Project;
import com.grad.eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/18 14:38
 **/
@Service
@Transactional(readOnly = true)
public class ProjectService {

    private Logger logger = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    private ProjectDao projectDao;

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
        List<Project> rows = projectDao.fuzzyQueryPaging(pageNumber, pageSize, projectName);
        init(rows);
        //查询总记录数
        Integer total = projectDao.findtotalByName(projectName);
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
        List<Project> rows = projectDao.initPaging(pageNumber, pageSize);
        //查询总记录数
        Integer total = projectDao.total();
        init(rows);
        //告知前端分页的数量
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
        Integer total = projectDao.findtotalByName(projectName);
        //根据参数查询数据
        List<Project> rows = projectDao.initQueryPaging(pageNumber,pageSize,projectName);
        init(rows);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 校验处理后添加新项目
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public QueryResultObject addProjectService(Project project, HttpSession session) {
        QueryResultObject msg = new QueryResultObject();
        User nowUser = (User)session.getAttribute("user");
        if(nowUser == null){
            msg.setMsg("登录过期！请重新登录！");
            msg.setResult(false);
            return msg;
        }
        String realName = nowUser.getRealName();
        if(project.getName() == null || "".equals(project.getName())){
            msg.setResult(false);
            msg.setMsg("传入值为空");
            return msg;
        }
        //校验Number是否存在
        Project projectResult = this.findByNumber(project.getNumber());
        if (projectResult != null ){
            msg.setResult(false);
            msg.setMsg("此项目编号已存在!");
            return msg;
        }
        //校验数据
        String checkResult = this.check(project);
        if ("错误".equals(checkResult)){
            msg.setResult(false);
            msg.setMsg("数据错误!");
            return msg;
        }
        //设置审核状态为：未审核
        project.setStatus(0);
        project.setcreateTime(new Date());
        project.setCreateUser(realName);
        try {
            this.addProject(project);
        }catch (Exception e){
            logger.error("插入Project表出错!" + "projectname= " + project.getName() + e.toString());
            msg.setResult(false);
            msg.setMsg("添加项目出错!" + "projectname= " + project.getName());
            return msg;
        }
        msg.setResult(true);
        msg.setMsg("插入成功!");
        return msg;
    }

    public Project findByNumber(Integer number) {
        return projectDao.findByNumber(number);
    }

    /**
     * 添加项目操作
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

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

    /**
     * 根据number查询 存在返回false 不存在返回true
     */
    public QueryResultObject queryByNumber(Integer number) {
        QueryResultObject msg = new QueryResultObject();
        Project projectResult = projectDao.findByNumber(number);
        if(projectResult != null){
            msg.setMsg(number + "已存在");
            msg.setResult(false);
        }else {
            msg.setResult(true);
        }
        return msg;
    }

    /**
     * 校验后进行更新项目
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
        if(!nowUser.getRealName().equals(project.getCreateUser())){
            msg.setResult(false);
            msg.setMsg("您不是该项目的拥有者，无权更改！");
            return msg;
        }
        if(project.getStatus() == 1){
            msg.setResult(false);
            msg.setMsg("已审核过的项目不能更改！");
            return msg;
        }
        Project projectResult = projectDao.findByNumber(project.getNumber());
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
            projectResult.setcreateTime(new Date());
            projectResult.setStatus(0);
            projectResult.setCreateUser(project.getCreateUser());
        }catch (Exception e){
            logger.error("项目编号创建后不可更改! 项目编号： " +project.getNumber() + e.toString());
            msg.setResult(false);
            msg.setMsg("项目编号创建后不可更改! 项目编号： " +project.getNumber());
            return msg;
        }
        try {
            this.updateProject(projectResult);
            msg.setResult(true);
            msg.setMsg("更新成功!");
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
        projectDao.updateProject(projectResult);
    }

    public Map<String,Object> limitQueryByName(Integer pageNumber, Integer pageSize, String realName) {
        Map<String, Object> map;
        if (realName != null && !"".equals(realName)) {
            map = this.initQueryPagingByName(pageNumber, pageSize,realName);
        } else {
            map = this.initPaging(pageNumber, pageSize);
        }
        return map;
    }

    /**
     * 根据用户名查询
     */
    public Map<String,Object> initQueryPagingByName(Integer pageNumber, Integer pageSize, String realName) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        //查询总记录数
        Integer total = projectDao.findtotalByUserName(realName);
        //根据参数查询数据
        List<Project> rows = projectDao.initQueryPagingByUserName(pageNumber,pageSize,realName);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     *  删除单个项目
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer delById(Integer id) {
        return projectDao.delById(id);
    }

    public Project findById(Integer id) {
        return projectDao.findById(id);
    }

    /**
     *  批量删除
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int batchDel(String[] array) {
        return projectDao.batchDel(array);
    }

    public List<Project> findByIds(String[] array) {
        List<Project> list = new ArrayList<>();
        try {
            for (String id : array) {
                System.out.println(id);
                list.add(this.findById(Integer.valueOf(id)));
            }
        }catch (Exception e){
            logger.error("查询project表出错！ ids=" +array);
            list.clear();
            return list;
        }
        return list;
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

