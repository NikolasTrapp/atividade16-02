package com.nikolastrapp.microservices.services;

import com.nikolastrapp.microservices.enums.StatusEmail;
import com.nikolastrapp.microservices.models.Email;
import com.nikolastrapp.microservices.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;


    public Email sendEmail(Email email) {

        try{
            email.setSendDateEmail(LocalDateTime.now());

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(email.getEmailFrom());

            message.setSubject(email.getSubject());

            message.setTo(email.getEmailTo());

            message.setText(email.getText());

            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException err) {
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(email);
        }

    }
}
