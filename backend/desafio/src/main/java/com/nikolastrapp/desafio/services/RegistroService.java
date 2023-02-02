package com.nikolastrapp.desafio.services;

import com.nikolastrapp.desafio.models.Registro;
import com.nikolastrapp.desafio.repositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll(){
        return registroRepository.findAll();
    }

    public Optional<Registro>  findById(UUID id){
        return registroRepository.findById(id);
    }

    public Registro save(Registro registro){
        return registroRepository.save(registro);
    }

    public void delete(Registro registro){
        registroRepository.delete(registro);
    }


}
