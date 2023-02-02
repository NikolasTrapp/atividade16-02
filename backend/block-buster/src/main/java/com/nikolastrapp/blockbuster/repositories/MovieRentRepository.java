package com.nikolastrapp.blockbuster.repositories;

import com.nikolastrapp.blockbuster.models.MovieRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRentRepository extends JpaRepository<MovieRent, UUID> {
}