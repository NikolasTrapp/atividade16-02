package com.nikolastrapp.microservices.repositories;

import com.nikolastrapp.microservices.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}