package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.eneity.Project;
import com.grad.eneity.User;
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
import java.util.List;
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
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, String projectName){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = ps.limitQuery(pageNumber, pageSize, projectName);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }
    /**
     * 用户名查询
     */
    @RequestMapping("/initPagingByName")
    @ResponseBody
    public QueryResultObject initPagingByName(Integer pageNumber,Integer pageSize,String realName){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = ps.limitQueryByName(pageNumber, pageSize, realName);
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
    public QueryResultObject add(Project project, HttpSession session){
        return ps.addProjectService(project,session);
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
     * 修改项目
     */
    @RequestMapping("/edit")
    @ResponseBody
    public QueryResultObject edit(Project project,HttpSession session){
        return ps.updateProjectService(project, session);
    }
    /**
     * 单个删除项目
     */
    @RequestMapping("/del")
    @ResponseBody
    public QueryResultObject del(String name,Integer id,HttpSession session){
        QueryResultObject object = new QueryResultObject();
        User nowUser = (User) session.getAttribute("user");
        if(nowUser == null){
            object.setMsg("登录过期！请重新登录！");
            object.setResult(false);
            return object;
        }
        Project p = ps.findById(id);
        if(p!=null){
            //不能删除他人的项目
            if(!p.getCreateUser().equals(nowUser.getRealName())){
                object.setMsg("你无权删除他人的项目！");
                object.setResult(false);
                return object;
            }
            //已审核的不能删除
            if(p.getStatus() == 1){
                object.setMsg("已审核通过的不能删除！");
                object.setResult(false);
                return object;
            }
            Integer integer = ps.delById(id);
            if (integer > 0) {
                object.setResult(true);
                object.setMsg("删除成功");
            } else {
                object.setResult(false);
                object.setMsg("删除失败，请稍后尝试");
            }
        }
        return object;
    }
    /**
     * 批量删除项目
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public QueryResultObject batchDel(String ids,HttpSession session){
        QueryResultObject object = new QueryResultObject();
        User nowUser = (User) session.getAttribute("user");
        if(nowUser == null){
            object.setMsg("登录过期！请重新登录！");
            object.setResult(false);
            return object;
        }
        if (ids != null && ids.length() > 0) {
            String[] array = ids.split(",");
            List<Project> list = ps.findByIds(array);
            for(Project p : list){
                if(!p.getCreateUser().equals(nowUser.getRealName())){
                    object.setMsg("您选择的项目中包含创建者不是您的！");
                    object.setResult(false);
                    return object;
                }
                if(p.getStatus() == 1){
                    object.setResult(false);
                    object.setMsg("您选择的项目中包含已经审核通过的！");
                    return object;
                }
            }
            int counter = ps.batchDel(array);
            if (counter > 0 && counter == array.length) {
                object.setMsg("删除成功");
                object.setResult(true);
            } else {
                object.setMsg("部分删除成功");
                object.setResult(true);
            }
        } else {
            object.setResult(false);
            object.setMsg("请检查要删除的项目的准确性");
        }
        return object;
    }
}
