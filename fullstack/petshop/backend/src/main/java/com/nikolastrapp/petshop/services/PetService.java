package com.nikolastrapp.petshop.services;

import com.nikolastrapp.petshop.models.Pet;
import com.nikolastrapp.petshop.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;


    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    @Transactional
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

}
