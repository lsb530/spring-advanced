package com.boki.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV6Advice {

    // com.boki.aop.order 패키지와 하위 패키지이면서 클래스 이름 패턴이 *Service
//    @Around("com.boki.aop.order.aop.Pointcuts.allOrderService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("com.boki.aop.order.aop.Pointcuts.allOrderService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.boki.aop.order.aop.Pointcuts.allOrderService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {}, return={}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "com.boki.aop.order.aop.Pointcuts.allOrder()", returning = "result")
//    public void doReturn2(JoinPoint joinPoint, Object result) { // 호출 가능
//    public void doReturn2(JoinPoint joinPoint, String result) { // 호출 가능2
    public void doReturn2(JoinPoint joinPoint, Integer result) { // 호출 불가능(타입 불일치)
        log.info("[return2] {}, return2={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "com.boki.aop.order.aop.Pointcuts.allOrderService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, message={}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "com.boki.aop.order.aop.Pointcuts.allOrderService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }

}
