package com.boki.proxy.config.v1_proxy;

import com.boki.proxy.app.v2.OrderControllerV2;
import com.boki.proxy.app.v2.OrderRepositoryV2;
import com.boki.proxy.app.v2.OrderServiceV2;
import com.boki.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.boki.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.boki.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.boki.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace) {
        OrderControllerV2 orderControllerImpl = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerConcreteProxy(orderControllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 orderServiceImpl = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceConcreteProxy(orderServiceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }
}
