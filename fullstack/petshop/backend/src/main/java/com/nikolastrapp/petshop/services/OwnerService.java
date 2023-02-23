package com.nikolastrapp.petshop.services;

import com.nikolastrapp.petshop.models.Owner;
import com.nikolastrapp.petshop.repositories.OwnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Transactional
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Transactional
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

}
