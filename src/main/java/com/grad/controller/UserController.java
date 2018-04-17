package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.eneity.User;
import com.grad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/13 14:07
 **/

@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService us;

    /**
     *分页查询
     */
    @RequestMapping("/initPaging")
    @ResponseBody
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, String userName){
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = us.limitQuery(pageNumber, pageSize, userName);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }

    /**
     * 根据名称单个查询
     */
    @RequestMapping("queryByName")
    @ResponseBody
    public QueryResultObject queryByName(String userName){
        return us.findByName(userName);
    }

    //添加用户
    @RequestMapping("/add")
    @ResponseBody
    public QueryResultObject addUser(User user) {
        return us.addUserService(user);
    }

    //修改用户
    @RequestMapping("/update")
    @ResponseBody
    public QueryResultObject updateUser(User user){
        return us.updateUserService(user);
    }

    //删除用户
    @RequestMapping("del")
    @ResponseBody
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        String items = request.getParameter("delitems");
        String[] strs = items.split(",");
        for (int i = 0; i < strs.length; i++) {
            try {
                System.out.println("user:"+strs[i]);
                int a = Integer.parseInt(strs[i]);
                System.out.println("id:"+a);
                //us.delById(a);
            } catch (Exception e) {
            }
        }
    }

}
