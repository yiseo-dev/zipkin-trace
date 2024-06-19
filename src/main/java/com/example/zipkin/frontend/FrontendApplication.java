package com.example.zipkin.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FrontendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class,
                "--spring.application.name=frontend",
                "--server.port=8080");
    }
    @Bean
    public RestTemplate retTemplate() {
        return new RestTemplateBuilder().build();
    }
}
