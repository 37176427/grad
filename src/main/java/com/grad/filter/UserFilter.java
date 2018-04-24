package com.grad.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述 ：UserFilter
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 14:29
 **/
//todo 路径未完善
//@WebFilter(filterName = "userFilter", urlPatterns = "/view/**")
public class UserFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();
        System.out.println("过滤：" + url);
        PrintWriter out = httpServletResponse.getWriter();
        //如果是登录 注销页面 放行
        if ("/".equals(url) || url.contains("logout") || url.contains("login") || url.contains("to")) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            //已经登录 放行
            if (httpServletRequest.getSession().getAttribute("user") != null) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
            //拦截
            httpServletResponse.setContentType("text/html;charset=utf-8");

            String loginPage = "/";
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\">");
            builder.append("alert('你还没有登录！请登录后操作');");
            builder.append("window.top.location.href='");
            builder.append(loginPage);
            builder.append("';");
            builder.append("</script>");
            out.print(builder.toString());
            //httpServletResponse.sendRedirect("/tologin");
        }
        if (out != null) {
            out.close();
        }
    }
}
