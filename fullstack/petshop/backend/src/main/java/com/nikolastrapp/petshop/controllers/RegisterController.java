package com.nikolastrapp.petshop.controllers;

import com.nikolastrapp.petshop.models.Register;
import com.nikolastrapp.petshop.services.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public ResponseEntity<List<Register>> findAll() {
        return ResponseEntity.ok().body(registerService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Register> optionalRegister = registerService.findById(id);

        if (optionalRegister.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No register found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalRegister.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid Register register) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registerService.save(register));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Register> optionalRegister = registerService.findById(id);

        if (optionalRegister.isPresent()) {
            registerService.delete(optionalRegister.get());
            return ResponseEntity.ok().body("Deleted succesfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any register found with id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Register register) {
        Optional<Register> optionalRegister = registerService.findById(id);

        if (optionalRegister.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any register found with id " + id);
        } else {
            Register newRegister = optionalRegister.get();
            BeanUtils.copyProperties(register, newRegister);
            return ResponseEntity.ok().body(registerService.save(newRegister));
        }
    }


}
