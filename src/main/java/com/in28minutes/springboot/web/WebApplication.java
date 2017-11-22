package com.in28minutes.springboot.web;

import com.beeinstant.metrics.MetricsManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        MetricsManager.init("beeInstantDemo");
        ApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
    }
}