package com.shensha.springbootaophello.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect//使用@Aspect注解将一个java类定义为切面类
@Order(5)//标识aop切面的优先级,值越小,优先级越大
@Component
public class WebLogAspect {
    private Logger logger = Logger.getLogger(getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 使用@Pointcut定义一个切入点，可以是一个规则表达式，
     * 比如下例中某个package下的所有函数，也可以是一个注解等。
     */
    @Pointcut("execution(public * com.shensha.springbootaophello.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint)throws Throwable{
        startTime.set(System.currentTimeMillis());
        //接收请求,记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url"+request.getRequestURL());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret)throws Throwable{
        logger.info("response"+ret);
        logger.info("spend time"+(System.currentTimeMillis()-startTime.get()));
        startTime.remove();
    }
}
