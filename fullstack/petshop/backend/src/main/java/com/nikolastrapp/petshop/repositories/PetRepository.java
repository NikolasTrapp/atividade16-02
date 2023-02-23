package com.nikolastrapp.petshop.repositories;

import com.nikolastrapp.petshop.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
