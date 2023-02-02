package com.nikolastrapp.desafio.repositories;

import com.nikolastrapp.desafio.models.Mutante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MutanteRepository extends JpaRepository<Mutante, UUID> {
}
