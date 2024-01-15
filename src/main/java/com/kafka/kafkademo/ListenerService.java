package com.kafka.kafkademo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListenerService {

    @KafkaListener(topics = "output", groupId = "group_id")
    public void listen(@Payload String message) {

        System.out.println("Received Message in group - group_id: " + message);
    }
}
