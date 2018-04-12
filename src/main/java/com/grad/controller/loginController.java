package com.grad.controller;

import com.grad.eneity.User;
import com.grad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:49
 **/
@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    private UserService us;
    @RequestMapping(value = "check")
    public String checkUser(HttpServletRequest request,@RequestParam("name")String name,@RequestParam("password")String password, Model model){

        User u = us.findByNameAndPassword(name,password);
        if (u == null){
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }
        System.out.println(u);
        //登录成功 加入session
        request.getSession().setAttribute("user", u);
        return "index";
    }
}
