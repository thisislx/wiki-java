package com.wiki.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.wiki.util.RequestContext;
import com.wiki.util.SnowFlake;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /** 定义一个切点 */
    @Pointcut("execution( * com.wiki.controller.*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Resource
    private SnowFlake snowFlake;

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // // 增加日志流水号

        // // 开始打印请求日志
        // ServletRequestAttributes attributes = (ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes();
        // HttpServletRequest request = attributes.getRequest();
        // Signature signature = joinPoint.getSignature();
        // String name = signature.getName();

        // // 打印请求信息
        // LOG.info("------------- 开始 -------------");
        // LOG.info("请求地址: {} {}", request.getRequestURL().toString(),
        // request.getMethod());
        // LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        // LOG.info("远程地址: {}", request.getRemoteAddr());

        // RequestContext.setRemoteAddr(getRemoteIp(request));

        // // 打印请求参数
        // Object[] args = joinPoint.getArgs();
        // // LOG.info("请求参数: {}", JSONObject.toJSONString(args));

        // Object[] arguments = new Object[args.length];
        // for (int i = 0; i < args.length; i++) {
        // if (args[i] instanceof ServletRequest
        // || args[i] instanceof ServletResponse
        // || args[i] instanceof MultipartFile) {
        // continue;
        // }
        // arguments[i] = args[i];
        // }
        // // 排除字段，敏感字段或太长的字段不显示
        // String[] excludeProperties = { "password", "file" };
        // PropertyPreFilters filters = new PropertyPreFilters();
        // PropertyPreFilters.MySimplePropertyPreFilter excludefilter =
        // filters.addFilter();
        // excludefilter.addExcludes(excludeProperties);
        // LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestContext.setRemoteAddr(getRemoteIp(request));

        // 打印请求参数
        Object[] args = proceedingJoinPoint.getArgs();
        // LOG.info("请求参数: {}", JSONObject.toJSONString(args));
        System.out.print(args);
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = { "password", "file" };
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);

        long startTime = System.currentTimeMillis();
        // 继续处理
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        excludefilter.addExcludes(excludeProperties);

        String mes = String.format("[%s]%s, 请求参数：%s 远程地址%s, 耗时%d ms",
                request.getMethod(),
                request.getRequestURI(),
                JSONObject.toJSONString(arguments, excludefilter),
                request.getRemoteAddr(),
                System.currentTimeMillis() - startTime);
        LOG.info(mes);
        return result;
    }

    /**
     * 使用nginx做反向代理，需要用该方法才能取到真实的远程IP
     * 
     * @param request
     * @return
     */
    public String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
