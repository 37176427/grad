package com.grad.controller;

import com.grad.eneity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 描述 ：主页中转控制器
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:59
 **/
@Controller
public class ToController {
    //登录页
    @RequestMapping("/")
    public String defaultIndex(HttpSession session) {
        if (session.getAttribute("user") != null) {
            //用户已登录 重定向到首页
            return "/view/index";
        }
        return "/view/login";
    }

    //最外层框架首页
    @RequestMapping("toindex")
    public String index(HttpSession session) {
        if (checkUser(session)) {
            return "view/index";
        } else {
            return "/";
        }
    }

    //首页
    @RequestMapping("toShouye")
    public String show(HttpSession session) {
        if (checkUser(session)) {
            return "view/shouye";
        } else {
            return "/";
        }
    }

    //用户信息页
    @RequestMapping("toUserInfo")
    public String userInfo(HttpSession session) {
        if (checkUser(session)) {
            User nowUser = (User) session.getAttribute("user");
            if (2 == nowUser.getPermission()) {
                return "view/userInfo";
            }
        }
        return "/";
    }

    //项目管理页
    @RequestMapping("toProject")
    public String project(HttpSession session) {
        if (checkUser(session)) {
            return "view/project";
        } else {
            return "/";
        }
    }

    //项目审批页
    @RequestMapping("toProjectManager")
    public String toProjectManager(HttpSession session) {
        if (checkUser(session)) {
            User nowUser = (User) session.getAttribute("user");
            if (1 == nowUser.getPermission()) {
                return "view/projectManager";
            }
        }
        return "/";
    }

    //材料管理页
    @RequestMapping("toMaterial")
    public String toPaper(HttpSession session) {
        if (checkUser(session)) {
            return "view/material";
        } else {
            return "/";
        }
    }

    //统计分析页
    @RequestMapping("toStatAnalyze")
    public String toStatAnalyze(HttpSession session) {
        if (checkUser(session)) {
            return "view/statAnalyze";
        } else {
            return "/";
        }
    }

    /**
     * 检查是否已登录，已登录返回true，未登录返回false
     */
    private boolean checkUser(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
