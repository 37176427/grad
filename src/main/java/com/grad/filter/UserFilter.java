package com.grad.filter;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 14:29
 **/
@WebFilter(filterName = "userFilter", urlPatterns = "/*")
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
        //如果是登录页面 放行
        if (httpServletRequest.getRequestURI().contains("login")) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }else
        {
            //已经登录 放行
            if (httpServletRequest.getSession().getAttribute("user")!=null)
            {
                chain.doFilter(httpServletRequest, httpServletResponse);
            }
            //拦截
            httpServletRequest.getSession().setAttribute("msg","你还没有登录！");
            httpServletResponse.sendRedirect("tologin");
            return;
        }

    }
}
