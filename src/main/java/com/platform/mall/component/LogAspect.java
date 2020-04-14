package com.platform.mall.component;
import com.platform.mall.bean.SysLog;
import com.platform.mall.mapper.SysLogMapper;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//统一日志处理切面
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;


//记录每个请求的日志
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Pointcut("execution(public * com.platform.mall.controller.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        SysLog sysLog = new SysLog();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            sysLog.setActionName(log.value());
        }
        sysLog.setMessageIncoming(getParameter(request.getParameterMap()));
        sysLog.setActionUrl(request.getRequestURI());
        Object userName = request.getAttribute("userName");
        if(userName != null){
            sysLog.setUserName(userName.toString());
        }
        try {
            Object result = joinPoint.proceed();
            sysLog.setMessageReturned(result.toString());
            return result;
        }
        catch (Exception ex){
            sysLog.setType(2);
            sysLog.setMessageReturned(ex.toString());
            response.getWriter().write(Result.failed(ex.toString()).toString());
            return null;
        }
        finally {
            Long timespan = System.currentTimeMillis() - startTime;
            sysLog.setTimespan(timespan.intValue());
            sysLog.setCreateTime(new Date());
        }
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Map<String,String[]> map) {
       StringBuilder sb = new StringBuilder();
       sb.append("{");
       for(Map.Entry<String,String[]> entry:map.entrySet()){
           sb.append(entry.getKey());
           sb.append(":[");
           for(String s:entry.getValue()){
               sb.append(s);
               sb.append(",");
           }
           sb.append("],");
        }
        sb.append("}");
       return sb.toString();
    }
}
