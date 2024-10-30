package com.boki.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    // com.boki.aop.order 패키지 & 하위 패키지
    @Pointcut("execution(* com.boki.aop.order..*(..))")
    public void allOrder() { } // pointcut signature

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() { }

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void allOrderService() { }

}
