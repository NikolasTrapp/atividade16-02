package com.nikolastrapp.desafio.services;

import com.nikolastrapp.desafio.models.Mutante;
import com.nikolastrapp.desafio.repositories.MutanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MutanteService {

    @Autowired
    private MutanteRepository mutanteRepository;


    public List<Mutante> findAll() {
        return mutanteRepository.findAll();
    }

    public Optional<Mutante> findByUuid(UUID id) {
        return mutanteRepository.findById(id);
    }

    @Transactional
    public Mutante save(Mutante standard) {
        return mutanteRepository.save(standard);
    }

    public List<Mutante> insertAll(List<Mutante> mutantes) {
        return mutanteRepository.saveAll(mutantes);
    }

    @Transactional
    public void delete(Mutante standard) {
        mutanteRepository.delete(standard);
    }

    public List<Mutante> findByPoder(Integer poder) {
        if (poder == 1) return findAll().stream().filter(mutante -> mutante.getPoder().equalsIgnoreCase("nivel1")).collect(Collectors.toList());
        else if (poder == 2) return findAll().stream().filter(mutante -> mutante.getPoder().equalsIgnoreCase("nivel2")).collect(Collectors.toList());
        else if(poder == 3) return findAll().stream().filter(mutante -> mutante.getPoder().equalsIgnoreCase("nivel3")).collect(Collectors.toList());
        else return findAll().stream().filter(mutante -> mutante.getPoder().equalsIgnoreCase("sem poder")).collect(Collectors.toList());
    }
}
