package com.epam.gym.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerLoggingAspect {

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerBean() {
    }

    @Pointcut("execution(* com.epam.gym.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods() && controllerBean()")
    public Object logControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        var transactionId = generateTransactionId();
        TransactionIdHolder.setTransactionId(transactionId);
        logTransactionStart(transactionId);
        logRequestDetails();
        try {
            var result = joinPoint.proceed();
            logResponseDetails(result);
            logTransactionEnd(transactionId);
            return result;
        } catch (Exception e) {
            logTransactionError(transactionId, e);
            throw e;
        } finally {
            TransactionIdHolder.clearTransactionId();
        }
    }


    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    private void logTransactionStart(String transactionId) {
        log.info("Transaction {} started", transactionId);
    }

    private void logRequestDetails() {
        log.info("Request URL: {}, Method: {}", request.getRequestURI(), request.getMethod());
    }

    private void logResponseDetails(Object result) {
        log.info("Response Status: {}, Response Body: {}", response.getStatus(), result.toString());
    }

    private void logTransactionEnd(String transactionId) {
        log.info("Transaction {} completed successfully", transactionId);
    }

    private void logTransactionError(String transactionId, Exception e) {
        log.error("Transaction {} failed with error: {}", transactionId, e.getMessage());
    }
}
