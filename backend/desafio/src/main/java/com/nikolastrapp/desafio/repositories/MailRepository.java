package com.nikolastrapp.desafio.repositories;

import com.nikolastrapp.desafio.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailRepository extends JpaRepository<Mail, UUID> {

}