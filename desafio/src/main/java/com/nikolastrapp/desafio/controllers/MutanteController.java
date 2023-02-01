package com.nikolastrapp.desafio.controllers;


import com.nikolastrapp.desafio.dtos.MutanteDto;
import com.nikolastrapp.desafio.models.Mutante;
import com.nikolastrapp.desafio.services.MutanteService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/mutante")
public class MutanteController {

    @Autowired
    private MutanteService mutanteService;


    @GetMapping
    public ResponseEntity<List<Mutante>> findAll() {
        List<Mutante> list = mutanteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{poder}")
    public ResponseEntity<Object> findByPoder(@PathVariable("poder") Integer poder) {
        List<Mutante> mutantes = mutanteService.findByPoder(poder);

        if (mutantes.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutante not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mutantes);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid MutanteDto mutanteDto) {
        Mutante mutante = new Mutante();
        BeanUtils.copyProperties(mutanteDto, mutante);
        if (!mutante.estaDentro()){
            mutante.setDentro(true);
            return ResponseEntity.status(HttpStatus.CREATED).body(mutanteService.save(mutante));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O mutante já esta dentro!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<Mutante> mutanteOptional = mutanteService.findByUuid(id);
        if (mutanteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutante not found!");
        }
        mutanteService.delete(mutanteOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Mutante deleted successfully!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid MutanteDto mutanteDto) {

        Optional<Mutante> mutanteOptional = mutanteService.findByUuid(id);

        if (mutanteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mutante not found!");
        }

        Mutante mutante = mutanteOptional.get();
        BeanUtils.copyProperties(mutanteDto, mutante);
        mutante.setId(mutanteOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(mutanteService.save(mutante));

    }

}
