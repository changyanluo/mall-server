package com.platform.mall.component;

import com.platform.mall.dto.UserCache;
import com.platform.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Autowired
    private RedisService redisService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            //身份验证
            String sessionId = request.getSession().getId();
            if(redisService.hasKey(sessionId)){
                //身份验证通过，开始权限验证
                UserCache userCache = (UserCache)redisService.get(sessionId);
                String url = request.getRequestURI();
                if(!url.equals("/user/login")){
                    if(!userCache.getAuthorities().contains(url)){
                        response.getWriter().write(Result.failed("没有权限!").toString());
                        return;
                    }
                }
               filterChain.doFilter(servletRequest,servletResponse);
            }
            else{
                //身份验证失败，返回会话过期
                response.getWriter().write(Result.timeOut().toString());
            }
        }
        catch(Exception ex){
            response.getWriter().write(Result.failed(ex.toString()).toString());
        }
    }
}
