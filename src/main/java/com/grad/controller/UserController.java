package com.grad.controller;

import com.grad.eneity.User;
import com.grad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/13 14:07
 **/

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService us;
    private HttpSession session;

    @RequestMapping("findAll")
    public String userList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getPermission());
        if(user.getPermission()!=2){
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('登录失败！')");
            return "toframe";
        }
        List<User> list = us.findAll();
        request.getSession().setAttribute("list",list);
        return "userList";
    }

    @RequestMapping("add")
    public String userList()
    {
        return "addUser";
    }

    @RequestMapping("edit")
    public String edit(HttpServletRequest request,@RequestParam("id")Integer id)
    {
        User u = us.findUserById(id);
        request.getSession().setAttribute("u",u);
        return "editUser";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id")Integer id)
    {
        us.deleteUserById(id);
        return "findAll";
    }
}
