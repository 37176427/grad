package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.eneity.Project;
import com.grad.service.ProjectManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/19 15:01
 **/
@Controller
@RequestMapping("/projectManager")
public class ProjectManagerController {

    private Logger logger = LoggerFactory.getLogger(ProjectManagerController.class);

    @Autowired
    private ProjectManagerService pms;

    /**
     *分页查询
     */
    @RequestMapping("/initPaging")
    @ResponseBody
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, String projectName){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = pms.limitQuery(pageNumber, pageSize, projectName);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }
    /**
     * 审核（更新）
     */
    @RequestMapping("/check")
    @ResponseBody
    public QueryResultObject check(Project project, HttpSession session){
        return pms.updateProjectService(project,session);
    }
    /**
     * 已审核的
     */
    @RequestMapping("/initPagingByStatus")
    @ResponseBody
    public QueryResultObject hasChecked(Integer pageNumber,Integer pageSize,Integer status){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = pms.limitQueryByStatus(pageNumber, pageSize, status);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }
}
