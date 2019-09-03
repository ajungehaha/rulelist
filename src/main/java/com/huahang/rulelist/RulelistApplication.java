package com.huahang.rulelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.huahang.rulelist")
public class RulelistApplication {

    public static void main(String[] args) {
        SpringApplication.run(RulelistApplication.class, args);
    }

}
