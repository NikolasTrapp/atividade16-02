package com.nikolastrapp.blockbuster.repositories;

import com.nikolastrapp.blockbuster.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}