package com.nikolastrapp.microservices.controllers;

import com.nikolastrapp.microservices.dtos.EmailDTO;
import com.nikolastrapp.microservices.models.Email;
import com.nikolastrapp.microservices.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);

        emailService.sendEmail(email);

        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }


}
