package com.example.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {}

    @Before("anyPublicMethod() && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logAction(JoinPoint joinPoint) {
        System.out.println("Execute method " + joinPoint.getSignature().getName());
    }

}
