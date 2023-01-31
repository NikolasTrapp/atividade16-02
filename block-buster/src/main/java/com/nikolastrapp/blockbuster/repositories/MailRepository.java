package com.nikolastrapp.blockbuster.repositories;

import com.nikolastrapp.blockbuster.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MailRepository extends JpaRepository<Mail, UUID> {

}