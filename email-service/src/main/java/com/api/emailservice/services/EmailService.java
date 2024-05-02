package com.api.emailservice.services;

import com.api.emailservice.enums.StatusEmail;
import com.api.emailservice.models.EmailModel;
import com.api.emailservice.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom);
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

            System.out.println("Email enviado com sucesso!");

        } catch(MailException error) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }
}
