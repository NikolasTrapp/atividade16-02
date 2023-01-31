package com.nikolastrapp.blockbuster.services;

import com.nikolastrapp.blockbuster.models.Mail;
import com.nikolastrapp.blockbuster.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {


    @Autowired
    private MailRepository mailRepository;


    public List<Mail> findAll() {
        return mailRepository.findAll();
    }

    public Mail save(Mail mail) {
        return mailRepository.save(mail);
    }
}
