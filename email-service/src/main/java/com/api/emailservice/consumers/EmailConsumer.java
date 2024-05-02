package com.api.emailservice.consumers;

import com.api.emailservice.dtos.EmailDTO;
import com.api.emailservice.models.EmailModel;
import com.api.emailservice.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "topic_spring_ms" , groupId = "spring-ms")
    public void listenEmail(@Payload EmailDTO emailDTO) {
        System.out.println("Recebendo payload: " + emailDTO);
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        emailService.sendEmail(emailModel);
    }
}
