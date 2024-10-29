package com.boki.proxy.proxyfactory;

import com.boki.proxy.common.advice.TimeAdvice;
import com.boki.proxy.common.service.ServiceImpl;
import com.boki.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    @Test
    void interfaceProxy() {
        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("target.getClass()={}", target.getClass());
        log.info("proxy.getClass()={}", proxy.getClass());

        proxy.save();

        // ProxyFactory를 통해 만들어진 경우에만 검증 가능
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }
}
