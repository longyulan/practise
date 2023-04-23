package com.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author longyulan
 * @date 2023/4/14
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.practise")
public class PractiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PractiseApplication.class, args);
    }

}
