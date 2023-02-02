package com.nikolastrapp.desafio.services;

import com.nikolastrapp.desafio.models.Mail;
import com.nikolastrapp.desafio.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
