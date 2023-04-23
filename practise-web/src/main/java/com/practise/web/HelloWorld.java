package com.practise.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 *
 * @author longyulan
 * @date 2023/4/19
 */
@RestController
@RequestMapping("/mxn")
public class HelloWorld {

    @Value("${server.port}")
    private String port;

    @GetMapping("/say")
    public String sayHello() {
        return "hello Ya , port " + port;
    }
}
