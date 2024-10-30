package com.boki.aop.proxyvs;

import com.boki.aop.member.MemberService;
import com.boki.aop.member.MemberServiceImpl;
import com.boki.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(ProxyDIAspect.class)
//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK 동적 프록시
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // CGLIB 프록시
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void jdk_dynamic() {
        log.info("memberService class={}", memberService.getClass());
//        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
//        memberServiceImpl.hello("hello");
    }

    @Test
    void cglib() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
    }

}
