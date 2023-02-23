package com.nikolastrapp.petshop.controllers;

import com.nikolastrapp.petshop.models.Owner;
import com.nikolastrapp.petshop.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<Owner>> findAll() {
        return ResponseEntity.ok().body(ownerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Owner> optionalOwner = ownerService.findById(id);

        if (optionalOwner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No owner found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalOwner.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Owner owner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(owner));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Owner> optionalOwner = ownerService.findById(id);

        if (optionalOwner.isPresent()) {
            ownerService.delete(optionalOwner.get());
            return ResponseEntity.ok().body("Deleted succesfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any owner found with id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Owner owner) {
        Optional<Owner> optionalOwner = ownerService.findById(id);

        if (optionalOwner.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any owner found with id " + id);
        } else {
            Owner newOwner = optionalOwner.get();
            BeanUtils.copyProperties(owner, newOwner);
            return ResponseEntity.ok().body(ownerService.save(newOwner));
        }
    }


}
