package com.nikolastrapp.petshop.controllers;

import com.nikolastrapp.petshop.models.Pet;
import com.nikolastrapp.petshop.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> findAll() {
        return ResponseEntity.ok().body(petService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Pet> optionalPet = petService.findById(id);

        if (optionalPet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pet found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalPet.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Pet pet) {
        System.out.println(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(pet));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Pet> optionalPet = petService.findById(id);

        if (optionalPet.isPresent()) {
            petService.delete(optionalPet.get());
            return ResponseEntity.ok().body("Deleted succesfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any pet found with id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Pet pet) {
        Optional<Pet> optionalPet = petService.findById(id);

        if (optionalPet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any pet found with id " + id);
        } else {
            Pet newPet = optionalPet.get();
            BeanUtils.copyProperties(pet, newPet);
            return ResponseEntity.ok().body(petService.save(newPet));
        }
    }


}
