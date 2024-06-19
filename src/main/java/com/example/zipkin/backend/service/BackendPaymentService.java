package com.example.zipkin.backend.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BackendPaymentService {
//    이 방법은 새로운 스레드의 추적이 안 됨 따라서 TraceableExecutor에 executorService를 전달하도록 한다.
//    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ExecutorService executorService;

    // TraceableExecutor에 executorService 전달
    public BackendPaymentService(BeanFactory beanFactory){
        this.executorService = new TraceableExecutorService(
                beanFactory, Executors.newFixedThreadPool(10), "lazy-pool"
        );
    }
    @SneakyThrows
    @NewSpan(name = "backendPayment")
    public void payment(@SpanTag("payment-price")Integer price) {
        // 컨트롤러에서 호출한 스레드가 아닌 다른 스레드에서 타임슬립 메소드를 처리하고자 함
        executorService.submit(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                // 0.1초에서 0.6초 가량의 딜레이
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500) + 100);
                log.info("async approved : {} ", price);
            }
        });
        log.info("approved");
    }
}
