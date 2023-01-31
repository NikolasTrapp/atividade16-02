package com.nikolastrapp.supermarket.repositories;

import com.nikolastrapp.supermarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}