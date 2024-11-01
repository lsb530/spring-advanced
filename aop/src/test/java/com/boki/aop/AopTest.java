package com.boki.aop;

import com.boki.aop.order.OrderRepository;
import com.boki.aop.order.OrderService;
import com.boki.aop.order.aop.AspectV1;
import com.boki.aop.order.aop.AspectV2;
import com.boki.aop.order.aop.AspectV3;
import com.boki.aop.order.aop.AspectV4Pointcut;
import com.boki.aop.order.aop.AspectV5Order;
import com.boki.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TransactionAspect.class})
@Import(AspectV6Advice.class)
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(
            () -> orderService.orderItem("ex")
        ).isInstanceOf(IllegalStateException.class);
    }

}
