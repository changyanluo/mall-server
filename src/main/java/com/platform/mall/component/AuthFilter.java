package com.platform.mall.component;

import com.platform.mall.dto.UserCache;
import com.platform.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//请求过滤类，用于验证身份和权限
@Component
public class AuthFilter implements Filter {

    @Autowired
    private RedisService redisService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //解决跨域的问题
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,X-App-Id, X-Token");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        String url = request.getRequestURI();
        filterChain.doFilter(request, response);
        //身份验证
//        if(!"/user/login".equals(url)) {
//            String token = request.getHeader("Authorization");
//            if (redisService.hasKey(token)) {
//                //身份验证通过，开始权限验证
//                UserCache userCache = (UserCache) redisService.get(token);
//                request.setAttribute("userName", userCache.getUserName());
//                if (!userCache.getAuthorities().contains(url)) {
//                    response.getWriter().write(Result.failed("没有权限!").toString());
//                    return;
//                }
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                //身份验证失败，返回会话过期
//                response.getWriter().write(Result.timeOut().toString());
//            }
//            filterChain.doFilter(servletRequest, servletResponse);

//        }
//        else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
