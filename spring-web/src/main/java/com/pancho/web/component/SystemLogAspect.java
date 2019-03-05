package com.pancho.web.component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pancho.web.annotion.Log;

/**
 * 
 * @desc 切点类 
 */

@Aspect
@Component
public class SystemLogAspect {
    
    private  static  final Logger log = LoggerFactory.getLogger(SystemLogAspect. class);  
    
    //Controller层切点  
    @Pointcut("execution (* com.pancho.web.user..*.*(..))")  
    public  void controllerAspect() {  
    }  
    
    /**
     * 前置通知 用于拦截操作，在方法返回后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
	handleLog(joinPoint, null);
    }
 
    /**
     * 拦截异常操作，有异常时执行
     * 
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
	handleLog(joinPoint, e);
    }
 
    private void handleLog(JoinPoint joinPoint, Exception e) {
	try {
		// 获得注解
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
	    Log controllerLog = method.getAnnotation(Log.class);
	    
	    // 获得方法名称
	    String className = joinPoint.getTarget().getClass().getName();
	    String methodName = joinPoint.getSignature().getName();
	    String operationType = controllerLog.operationType();
	    String operationName = controllerLog.operationName();
	    //打印日志，如有需要还可以存入数据库
	    log.info(">>>>>>>>>>>>>模块名称：{}",operationName);
	    log.info(">>>>>>>>>>>>>操作名称：{}",operationType);
	    log.info(">>>>>>>>>>>>>类名：{}",className);
	    log.info(">>>>>>>>>>>>>方法名：{}",methodName);
	} catch (Exception exp) {
	    // 记录本地异常日志
	    log.error("==前置通知异常==");
	    log.error("异常信息:{}", exp.getMessage());
	    exp.printStackTrace();
	}
    }
 
    /**
     * 是否存在注解，如果存在就获取
     */
    private static Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
		/*Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();*/
    	
    	Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    	
		if (method != null) {
		    return method.getAnnotation(Log.class);
		}
		return null;
    }

}
  
    