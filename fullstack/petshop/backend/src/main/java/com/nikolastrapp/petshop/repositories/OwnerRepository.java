package com.nikolastrapp.petshop.repositories;

import com.nikolastrapp.petshop.models.Owner;
import com.nikolastrapp.petshop.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
