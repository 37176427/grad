package com.grad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述 ：错误页面controller
 * 作者 ：WangYunHe
 * 时间 ：2018/4/19 14:33
 **/
@Controller
public class MyErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @Override
    public String getErrorPath() {
        logger.warn("出错啦！进入自定义错误控制器");
        return "/error";
    }

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404) {
            return "error/404";
        } else {
            return "error/500";
        }
    }
}
