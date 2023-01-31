package com.nikolastrapp.supermarket.controllers;

import com.nikolastrapp.supermarket.dtos.ProductDto;
import com.nikolastrapp.supermarket.models.Product;
import com.nikolastrapp.supermarket.services.ProductService;
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
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        Optional<Product> optionalProduct = productService.findByUuid(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalProduct.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Product> optionalProduct = productService.findByUuid(id);

        if (optionalProduct.isPresent()) {
            productService.delete(optionalProduct.get());
            return ResponseEntity.ok().body("Deleted succesfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any product found with id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid ProductDto productDto) {
        Optional<Product> optionalProduct = productService.findByUuid(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any product found with id " + id);
        } else {
            Product product = optionalProduct.get();
            BeanUtils.copyProperties(productDto, product);
            return ResponseEntity.ok().body(productService.save(product));
        }
    }
}
