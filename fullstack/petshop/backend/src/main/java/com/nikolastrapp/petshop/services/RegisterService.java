package com.nikolastrapp.petshop.services;

import com.nikolastrapp.petshop.models.Register;
import com.nikolastrapp.petshop.repositories.RegisterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;


    public List<Register> findAll() {
        return registerRepository.findAll();
    }

    public Optional<Register> findById(Long id) {
        return registerRepository.findById(id);
    }

    @Transactional
    public Register save(Register register) {
        return registerRepository.save(register);
    }

    @Transactional
    public void delete(Register register) {
        registerRepository.delete(register);
    }

}
