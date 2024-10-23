package com.boki.advanced.app.v5;

import com.boki.advanced.trace.callback.*;
import com.boki.advanced.trace.logtrace.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v5")
@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/request")
    public String request(@RequestParam("itemId") String itemId) {
        return template.execute("OrderController.request()", new TraceCallback<String>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}
