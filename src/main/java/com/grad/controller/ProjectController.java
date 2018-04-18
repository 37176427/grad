package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.eneity.Project;
import com.grad.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Map;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/18 14:37
 **/
@Controller
@RequestMapping("/project/pro")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService ps;

    /**
     *分页查询
     */
    @RequestMapping("/initPaging")
    @ResponseBody
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, Integer number){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = ps.limitQuery(pageNumber, pageSize, number);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }
    /**
     * 添加项目
     */
    @RequestMapping("/add")
    @ResponseBody
    public QueryResultObject add(Project project, HttpServletRequest request){
        return ps.addProjectService(project,request);
    }
    /**
     * 根据编号查询项目
     */
    @RequestMapping("/queryByNumber")
    @ResponseBody
    public QueryResultObject queryByNumber(Integer number){
        return ps.queryByNumber(number);
    }

    /**
     * 修改xiangmu
     */
    @RequestMapping("/edit")
    @ResponseBody
    public QueryResultObject edit(Project project,HttpSession session){
        return ps.updateProjectService(project, session);
    }
}
