package com.api.emailservice.consumers;

import com.api.emailservice.dtos.EmailDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    @KafkaListener(topics = "topic_spring_ms" , groupId = "spring-ms")
    public void listenEmail(@Payload EmailDTO message) {
        System.out.println("Received Message: " + message);
    }
}
