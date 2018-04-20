package com.grad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述 ：中转控制器
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:59
 **/
@Controller
public class ToController {
    //登录页
    @RequestMapping("/")
    public String defaultIndex(){return "/view/login";}

   /* @RequestMapping("tologin")
    public String login(){
        return "view/login";
    }*/
    @RequestMapping("toindex")
    public String index(){
        return "view/index";
    }
    @RequestMapping("toShouye")
    //首页
    public String show(){
        return "view/shouye";
    }
    //用户信息页
    @RequestMapping("toUserInfo")
    public String userInfo(){
        return "view/userInfo";
    }
    //项目管理页
    @RequestMapping("toProject")
    public String project(){ return "view/project"; }
    //项目审批页
    @RequestMapping("toProjectManager")
    public String toProjectManager(){
        return "view/projectManager";
    }
    //论文管理页
    @RequestMapping("toMaterial")
    public String toPaper(){ return "view/material";}

}
