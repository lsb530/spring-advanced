package com.boki.proxy;

import com.boki.proxy.config.AppV1Config;
import com.boki.proxy.config.AppV2Config;
import com.boki.proxy.config.v1_proxy.ConcreteProxyConfig;
import com.boki.proxy.config.v1_proxy.InterfaceProxyConfig;
import com.boki.proxy.trace.logtrace.LogTrace;
import com.boki.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
// config 패키지 하위의 Configuration들이 컴포넌트 스캔에 잡히지 않게 하기 위함
//@Import(InterfaceProxyConfig.class)
@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "com.boki.proxy.app.v3")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
