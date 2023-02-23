package com.nikolastrapp.petshop.repositories;

import com.nikolastrapp.petshop.models.Pet;
import com.nikolastrapp.petshop.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
