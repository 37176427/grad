package com.grad.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/13 17:11
 **/
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class sessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).setHeader("P3P","CP=CAO PSA OUR");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
