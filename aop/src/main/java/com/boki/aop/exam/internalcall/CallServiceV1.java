package com.boki.aop.exam.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    // @Lazy가 없으면 순환참조 오류 발생
//    @Lazy
//    public CallServiceV1(CallServiceV1 callServiceV1) {
//        log.info("CallServiceV1 setter={}", callServiceV1.getClass());
//        this.callServiceV1 = callServiceV1;
//    }

    @Autowired // 현재 안됨!! Spring 2.6부터 순환참조 금지됨 속성 수정하면 가능(spring.main.allow-circular-reference=true로)
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("CallServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }

}
