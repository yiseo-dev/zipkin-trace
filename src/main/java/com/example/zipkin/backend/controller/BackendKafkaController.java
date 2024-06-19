package com.example.zipkin.backend.controller;

import com.example.zipkin.backend.service.BackendKafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BackendKafkaController {
    private final BackendKafkaService backendKafkaService;

    @GetMapping("/kafka/order/{orderNumber}")
    public String order(@PathVariable Integer orderNumber) {
        log.info("controller : {}", orderNumber);
        backendKafkaService.payment(orderNumber * 10);
        return "OK" + orderNumber;
    }

    // order/orderNumber --> service.payment --> kafka produce --> kafka save -->
    // backend consume --> logging
}
