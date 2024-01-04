package com.epam.gym.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@within(org.springframework.stereotype.Repository)" +
            " || @within(org.springframework.stereotype.Service )")
    public void springBeanPointcut() {
    }

    @Pointcut("within(com.epam.gym.dao..*)" +
            " || within(com.epam.gym.service..*)")
    public void applicationPackagePointcut() {
    }

    @Pointcut("execution(* com.epam.gym.*.getById(Long))")
    public void findByIdMethods() {
    }

    @Pointcut("execution(* com.epam.gym.*.create(..))")
    public void createMethods() {
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception: {} in {}.{}() with cause = {}", ex.getClass().getSimpleName(), joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage() != null ? ex.getMessage() : "");
    }

    @Before("springBeanPointcut() && findByIdMethods()")
    public void logFindById(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        log.info("Calling method: {} with id: {}",
                methodName,
                joinPoint.getArgs());
    }

    @Before("springBeanPointcut() && createMethods()")
    public void logCreate(JoinPoint joinPoint) {
        var methodName = joinPoint.getSignature().getName();
        var args = joinPoint.getArgs();
        log.info("c: {} with first name and last name: {}", methodName, args[1]);
    }


    @AfterReturning(pointcut = "springBeanPointcut()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        var methodName = joinPoint.getSignature().getName();
        log.info("Exiting method {}} with result: {}", methodName, result);
    }


}