package com.boki.proxy.trace.callback;

public interface TraceCallback<T> {
    T call();
}