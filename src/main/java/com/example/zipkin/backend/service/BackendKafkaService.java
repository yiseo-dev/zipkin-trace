package com.example.zipkin.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BackendKafkaService {
    private final KafkaTemplate<String,String> kafkaTemplate;
    @SneakyThrows
    @NewSpan(name = "kafkaPayment")
    public void payment(@SpanTag("payment-price") int price) {
        // produce message
        kafkaTemplate.send("backend", price + "원이 결제 요청됩니다.");

        log.info("approved");
    }

    @KafkaListener(topics = "backend", groupId = "backend-c1")
    public void consume(ConsumerRecord<String,String> record) {
        log.info("consume : {} 그리고 결제 완료", record.value());
    }
}
