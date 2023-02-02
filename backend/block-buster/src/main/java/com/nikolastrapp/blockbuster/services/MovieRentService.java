package com.nikolastrapp.blockbuster.services;

import com.nikolastrapp.blockbuster.models.MovieRent;
import com.nikolastrapp.blockbuster.repositories.MovieRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieRentService {

    @Autowired
    private MovieRentRepository movieRentRepository;

    public List<MovieRent> findAll(){
        return movieRentRepository.findAll();
    }

    public Optional<MovieRent> findById(UUID id){
        return movieRentRepository.findById(id);
    }

    public MovieRent save(MovieRent movieRent){
        return movieRentRepository.save(movieRent);
    }

    public void delete (UUID id){
        movieRentRepository.deleteById(id);
    }

    public MovieRent update (MovieRent movieRent){
        return movieRentRepository.save(movieRent);
    }

}
