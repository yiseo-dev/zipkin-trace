package com.example.zipkin.backend.controller;

import com.example.zipkin.backend.service.BackendPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@Slf4j
public class BackendController {
    private final BackendPaymentService backendPaymentService;
    @GetMapping("/order/{orderNumber}")
    public String order(@PathVariable Integer orderNumber) {
//        long time = System.nanoTime();
//        if(time % 9 < 0.5) {
//            throw new RuntimeException("error");
//        }
//        log.info("{}: controller: {}",time, orderNumber);
//        backendPaymentService.payment(time, orderNumber * 10);
        /*************************
         * 1) service단의 payment 메소드는
         * 0.1초 - 0.5초 딜레이 발생
         * 2) controller의 응답 또한
         * 0.1초 - 0.5초 딜레이 발생
         * BUT Controller의 응답을 즉시 받기를 원함
         * payment는 응답이 없는 void기 때문에 payment는 비동기 처리를 한다.
         * (즉 새로운 스레드에서 동작하게끔 할 것이다.
        * ************************/
        log.info("contoller : {} ", orderNumber);
        backendPaymentService.payment(orderNumber * 10);
        return "OK " + orderNumber;
    }
}
