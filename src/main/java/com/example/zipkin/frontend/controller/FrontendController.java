package com.example.zipkin.frontend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
@RequiredArgsConstructor
public class FrontendController {
    private final RestTemplate restTemplate;

    @GetMapping("/order/request")
    public String request() {
        log.info("frontend");
        String response = restTemplate.getForObject("http://localhost:8081/order/100", String.class);
        return response;
    }
}
