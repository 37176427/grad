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
    @RequestMapping("tologin")
    public String login(){
        return "login";
    }
    @RequestMapping("toindex")
    public String index(){
        return "index";
    }
    @RequestMapping("toframe")
    public String frame(){
        return "frame";
    }
}
