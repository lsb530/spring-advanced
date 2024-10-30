package com.boki.proxy.config.v6_aop.aspect;

import com.boki.proxy.trace.TraceStatus;
import com.boki.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    // 어드바이저: 포인트컷 + 어드바이스
    // 포인트컷
    @Around("execution(* com.boki.proxy.app..*(..)) && !execution(* com.boki.proxy.app..noLog(..))") // AspectJ EL
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 어드바이스
        TraceStatus status = null;

        log.info("target: {}", joinPoint.getTarget()); // 실제 호출 대상
        log.info("getArgs: {}", joinPoint.getArgs()); // 전달인자
        log.info("getSignature: {}", joinPoint.getSignature()); // jointPoint 시그니처

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
