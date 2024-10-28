package com.boki.proxy.app.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 스프링부트 2.x: @Controller 또는 @RequestMapping이 있어야 RequestMappingHandlerMapping이 스프링 컨트롤러로 인식
// 스프링부트 3.x(스프링 6.0): @Controller 또는 @RestController가 있어야 RequestMappingHandlerMapping이 스프링 컨트롤러로 인식
//@RequestMapping
@Controller
@ResponseBody
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();

}
