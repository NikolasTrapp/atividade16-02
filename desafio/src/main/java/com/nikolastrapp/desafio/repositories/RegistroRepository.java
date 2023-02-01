package com.nikolastrapp.desafio.repositories;

import com.nikolastrapp.desafio.models.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroRepository extends JpaRepository<Registro, UUID> {
}