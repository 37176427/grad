package com.grad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:59
 **/
@Controller
public class ToController {
    //登录页
    @RequestMapping("tologin")
    public String login(){
        return "view/login";
    }
    @RequestMapping("toindex")
    public String index(){
        return "view/index";
    }
    @RequestMapping("show")
    //首页
    public String show(){
        return "view/shouye";
    }
    //用户信息页
    @RequestMapping("userInfo")
    public String userInfo(){
        return "view/userInfo";
    }

}
