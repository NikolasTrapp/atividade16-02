package com.nikolastrapp.supermarket.repositories;

import com.nikolastrapp.supermarket.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
}