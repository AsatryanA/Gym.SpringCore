package com.epam.gym.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerBean() {
    }

    @Pointcut("execution(* com.epam.gym.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Before("controllerMethods() && controllerBean()")
    public void logBefore(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        var className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Calling method '{}' in class '{}'", methodName, className);
    }

    @AfterReturning(pointcut = "controllerMethods() && controllerBean()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        var methodName = joinPoint.getSignature().getName();
        var className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("Method '{}' in class '{}' executed successfully with result: {}", methodName, className, result);
    }
}
