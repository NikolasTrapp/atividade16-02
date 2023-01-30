package com.nikolastrapp.blockbuster.controllers;

import com.nikolastrapp.blockbuster.dtos.CustomerDto;
import com.nikolastrapp.blockbuster.models.Customer;
import com.nikolastrapp.blockbuster.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok().body(customerService.findAll());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById (@PathVariable UUID id){
        Optional<Customer> optionalCustomer = customerService.findById(id);

        if (optionalCustomer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer found with UUID \"" + id + "\"");
        } else {
            return ResponseEntity.ok().body(optionalCustomer.get());
        }

    }


    @PostMapping
    public ResponseEntity<Customer> save (@RequestBody @Valid CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete (@PathVariable UUID id){
        try{
            customerService.delete(id);
            return ResponseEntity.ok().body("Entity deleted successfully");
        } catch (EntityNotFoundException err) {
            return ResponseEntity.ok().body(err.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update (@PathVariable(value = "id") UUID id, @RequestBody @Valid CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        try{
            return ResponseEntity.ok().body(customerService.update(id, customer));
        } catch (EntityNotFoundException err){
            return ResponseEntity.ok().body(err.getMessage());
        }
    }


}
