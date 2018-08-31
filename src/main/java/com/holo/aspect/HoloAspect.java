package com.holo.aspect;

import com.holo.annotation.lock.HoloLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class HoloAspect {

    @Pointcut("execution(public * com.holo.controller.UserRestController.*(..))")
    public void annotationPointCut() {

    }

    @Before("execution(public * com.holo.controller.UserRestController.*(..))")
    public void before(JoinPoint joinPoint) {
       MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        HoloLog holoLog = method.getAnnotation(HoloLog.class);
        System.out.print("holoLog = " + holoLog.value());

    }
}
