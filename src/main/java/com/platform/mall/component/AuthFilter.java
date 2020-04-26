package com.platform.mall.component;

import com.platform.mall.common.Util;
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
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if("/user/login".equals(url) || "OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            //身份验证
            String token = request.getHeader("Authorization");
            if (token != null && redisService.hasKey(Util.ONLINE_USER_PREFIX + token)) {
                //身份验证通过，开始权限验证
                UserCache userCache = (UserCache) redisService.get(Util.ONLINE_USER_PREFIX + token);
                request.setAttribute("userName", userCache.getUserName());
                request.setAttribute("userId",userCache.getUserId());
                if (!userCache.getAuthorities().contains(url)) {
                    response.getWriter().write("No Authority for action " + url);
                    return;
                }
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //身份验证失败，返回会话过期
                response.getWriter().write("session timeout!");
            }
        }
    }
}
