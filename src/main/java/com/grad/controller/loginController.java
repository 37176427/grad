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

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:49
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService us;

    private HttpSession session;

    @RequestMapping(value = "/login")
    public String checkUser(HttpServletRequest request,HttpServletResponse response,@RequestParam("name")String name, @RequestParam("password")String password) throws IOException {
        session = request.getSession();
        PrintWriter out = response.getWriter();
        User u = us.findByNameAndPassword(name,password);
        if (u == null){
            out.print("<script>alert('用户名或密码错误！')</script>");
            return "view/login";
        }
        System.out.println(u+"已登录");
        //登录成功 加入session
        session.setAttribute("user", u);
        return "view/index";
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        session = request.getSession();
        session.invalidate();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String loginPage = "/tologin";
        StringBuilder builder = new StringBuilder();
        builder.append("<script type=\"text/javascript\">");
        builder.append("alert('注销成功！');");
        //top失效 parent有效
        builder.append("window.parent.location.href='");
        builder.append(loginPage);
        builder.append("';");
        builder.append("</script>");
        out.print(builder.toString());
    }
}
