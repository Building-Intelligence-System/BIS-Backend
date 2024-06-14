package com.business.intelligence.service.aop;

import jakarta.security.auth.message.AuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class JwtAuthAspect {

    @Pointcut("execution(* com.business.intelligence.service.controller.*.*(..))")
    void controllerMethod() {
        //
    }

    @Pointcut("execution(* com.business.intelligence.service.controller.PersonController.create(..))")
    void personCreateMethod() {
        //
    }

    @Pointcut("execution(* com.business.intelligence.service.controller.PersonController.auth(..))")
    void personAuthMethod() {
        //
    }

    @Before("controllerMethod() && !(personCreateMethod() || personAuthMethod())")
    public void jwtCheck(JoinPoint joinPoint) throws AuthException {
        final HttpHeaders arg = (HttpHeaders) joinPoint.getArgs()[0];
//        if (!arg.containsKey("Authorization")) {
//            throw new AuthException("Auth required");
//        }
    }
}
