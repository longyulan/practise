package com.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author longyulan
 * @date 2023/4/14
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.practise")
public class PractiseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PractiseApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PractiseApplication.class);
    }
}
