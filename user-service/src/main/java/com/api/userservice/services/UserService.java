package com.api.userservice.services;

import com.api.userservice.models.UserModel;
import com.api.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Transactional
    public UserModel saveAndPublish(UserModel userModel) {
        emailService.publishMessageEmail(userModel);
        return userRepository.save(userModel);
    }
}
