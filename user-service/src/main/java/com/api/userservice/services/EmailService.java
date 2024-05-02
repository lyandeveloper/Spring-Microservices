package com.api.userservice.services;

import com.api.userservice.dtos.EmailDTO;
import com.api.userservice.models.UserModel;
import com.api.userservice.services.kafka.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    KafkaService producer;

    public void publishMessageEmail(UserModel userModel) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setUserId(userModel.getId());
        emailDTO.setEmailTo(userModel.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite todos os recursos da nossa plataforma!");
        producer.send(emailDTO);
    }
}
