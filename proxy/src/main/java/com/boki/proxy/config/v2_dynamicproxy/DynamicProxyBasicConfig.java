package com.boki.proxy.config.v2_dynamicproxy;

import com.boki.proxy.app.v1.OrderControllerV1;
import com.boki.proxy.app.v1.OrderControllerV1Impl;
import com.boki.proxy.app.v1.OrderRepositoryV1;
import com.boki.proxy.app.v1.OrderRepositoryV1Impl;
import com.boki.proxy.app.v1.OrderServiceV1;
import com.boki.proxy.app.v1.OrderServiceV1Impl;
import com.boki.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.boki.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));
        return (OrderControllerV1) Proxy.newProxyInstance(
            OrderControllerV1.class.getClassLoader(),
            new Class[]{OrderControllerV1.class},
            new LogTraceBasicHandler(orderControllerV1, logTrace)
        );
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderServiceV1 = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        return (OrderServiceV1) Proxy.newProxyInstance(
            OrderServiceV1.class.getClassLoader(),
            new Class[]{OrderServiceV1.class},
            new LogTraceBasicHandler(orderServiceV1, logTrace)
        );
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
            OrderRepositoryV1.class.getClassLoader(),
            new Class[]{OrderRepositoryV1.class},
            new LogTraceBasicHandler(orderRepository, logTrace)
        );
    }

}
