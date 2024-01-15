package com.kafka.kafkademo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic sendTopic() {
        return TopicBuilder.name("send")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("output")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
