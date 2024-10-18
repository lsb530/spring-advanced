package com.boki.advanced.trace.hellotrace;

import com.boki.advanced.LoggingExtension;
import com.boki.advanced.trace.TraceStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(LoggingExtension.class)
class HelloTraceV2Test {

//    @BeforeAll
//    public static void setErrorLogging() {
//        LoggingSystem.get(ClassLoader.getSystemClassLoader()).setLogLevel(Logger.ROOT_LOGGER_NAME, LogLevel.DEBUG);
//    }

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}