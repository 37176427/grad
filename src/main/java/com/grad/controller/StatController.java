package com.grad.controller;

import com.grad.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述 ：统计页面controller
 * 作者 ：WangYunHe
 * 时间 ：2018/4/24 10:48
 **/
@Controller
public class StatController {
    @Autowired
    private StatService statService;

    @RequestMapping("/stat/material")
    @ResponseBody
    public String getStatByMaterial() {
        return statService.getStatByMaterial();
    }

    @RequestMapping("/stat/checked")
    @ResponseBody
    public String getStatByChecked() {
        return statService.getStatByChecked();
    }
}
