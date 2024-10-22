package com.boki.advanced.app.v4;

import com.boki.advanced.trace.logtrace.*;
import com.boki.advanced.trace.template.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v4")
@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/request")
    public String request(@RequestParam("itemId") String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
