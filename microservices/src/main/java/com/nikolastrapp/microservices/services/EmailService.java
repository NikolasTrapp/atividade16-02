package com.nikolastrapp.microservices.services;

import com.nikolastrapp.microservices.models.Email;
import com.nikolastrapp.microservices.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;


    public void sendEmail(Email email) {
    }
}
