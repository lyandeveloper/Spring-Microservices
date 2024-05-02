package com.api.userservice.services.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class KafkaService {
    @Value(value = "${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Serializable message) {
        System.out.printf("Payload enviado: %s", message);
        kafkaTemplate.send(topicName, message);
    }
}
