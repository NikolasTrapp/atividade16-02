package com.nikolastrapp.blockbuster.controllers;

import com.nikolastrapp.blockbuster.dtos.MovieRentDto;
import com.nikolastrapp.blockbuster.models.MovieRent;
import com.nikolastrapp.blockbuster.services.MovieRentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(name = "/movie-controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovieRentController {

    @Autowired
    private MovieRentService movieRentService;


    @GetMapping
    public ResponseEntity<List<MovieRent>> findAll(){
        return ResponseEntity.ok().body(movieRentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            return ResponseEntity.ok().body(optionalMovieRent.get());
        }
    }

    @PostMapping
    public ResponseEntity<MovieRent> save (@RequestBody @Valid MovieRentDto movieRentDto){
        MovieRent movieRent = new MovieRent();
        BeanUtils.copyProperties(movieRentDto, movieRent);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieRentService.save(movieRent));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            movieRentService.delete(id);
            return ResponseEntity.ok().body("Movie rent deleted!");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid MovieRentDto movieRentDto){
        Optional<MovieRent> optionalMovieRent = movieRentService.findById(id);

        if (optionalMovieRent.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie rent found with UUID \"" + id + "\"");
        } else {
            MovieRent movieRent = new MovieRent();
            BeanUtils.copyProperties(movieRentDto, movieRent);
            return ResponseEntity.status(HttpStatus.OK).body(movieRentService.update(movieRent));
        }
    }


}

