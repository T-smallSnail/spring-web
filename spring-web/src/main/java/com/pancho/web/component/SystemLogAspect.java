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
    
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
    	beforHand(joinPoint);
     }
    
    
    /**
     * 前置通知 用于拦截操作，在方法返回后执行
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "controllerAspect()")
    public void afterReturn(JoinPoint joinPoint) {
    	handleLog(joinPoint);
    }
 
    
    
 
    private void handleLog(JoinPoint joinPoint) {
	try {
		// 获得注解
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
	    Log controllerLog = method.getAnnotation(Log.class);
	    
	    // 获得方法名称
	    String className = joinPoint.getTarget().getClass().getName();
	    String methodName = joinPoint.getSignature().getName();
	    String operationType = controllerLog.operationType();
	    String operationName = controllerLog.operationName();
	    
	    //获取入参
	    
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
 
    
    
    
    
    private void beforHand(JoinPoint joinPoint) {
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
	    
	    //获取入参
	    log.info(">>>>>>>>>>>>>{}",parseParames(joinPoint.getArgs()));
	    
	    
	    
	    
	} catch (Exception exp) {
	    // 记录本地异常日志
	    log.error("==前置通知异常==");
	    log.error("异常信息:{}", exp.getMessage());
	    exp.printStackTrace();
	}
    }
    
    
    
    private String parseParames(Object[] parames) {

        if (null == parames || parames.length <= 0) {
            return "";

        }
        StringBuffer param = new StringBuffer("传入参数 # 个:[ ");
        int i =0;
        for (Object obj : parames) {
            i++;
            if (i==1){
                param.append(obj.toString());
                continue;
            }
            param.append(" ,").append(obj.toString());
        }
        return param.append(" ]").toString().replace("#",String.valueOf(i));
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
  
    
