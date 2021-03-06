package com.platform.mall.component;
import com.platform.mall.bean.SysLog;
import com.platform.mall.common.Util;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//统一日志处理切面
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;


//记录每个请求的日志,先发送到kafka,再转到es
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

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
        sysLog.setIp(Util.getIpAddr(request));
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            sysLog.setActionName(log.value());
        }
        sysLog.setMessageIncoming(Util.toJsonString(request.getParameterMap()));
        sysLog.setActionUrl(request.getRequestURI());
        Object userName = request.getAttribute("userName");
        if(userName != null){
            sysLog.setUserName(userName.toString());
        }
        try {
            Object result = joinPoint.proceed();
            sysLog.setMessageReturned(Util.toJsonString(result));
            return result;
        }
        catch (Exception ex){
            sysLog.setMessageReturned(ex.getMessage());
            StringBuilder sbException = new StringBuilder();
            for (StackTraceElement ele : ex.getStackTrace()) {
                sbException.append(MessageFormat.format("\tat {0}.{1}({2}:{3})\n",
                        ele.getClassName(), ele.getMethodName(), ele.getFileName(), ele.getLineNumber()));;
            }
            sysLog.setStack(sbException.toString());
            response.getWriter().write(ex.getMessage());
            return null;
        }
        finally {
            Long timespan = System.currentTimeMillis() - startTime;
            sysLog.setTimespan(timespan.intValue());
            sysLog.setCreateTime(new Date());
            try{
                kafkaTemplate.send("log",Util.toJsonString(sysLog));
            }
            catch (Exception ex){
                logger.error("发送操作日志到kafka失败:"+ ex.getMessage());
            }
        }
    }
}
