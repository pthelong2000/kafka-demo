package com.kafka.kafkademo;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaTemplate<String, DataRequest> kafkaTemplate;

    @PostMapping("/api/send")
    public void sendMessage(@RequestBody DataRequest data) {
        kafkaTemplate.send("send", "1", data);
    }
}
