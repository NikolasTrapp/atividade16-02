package com.nikolastrapp.supermarket.controllers;

import com.nikolastrapp.supermarket.dtos.BrandDto;
import com.nikolastrapp.supermarket.models.Brand;
import com.nikolastrapp.supermarket.services.BrandService;
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
@RequestMapping(value = "brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping
    public ResponseEntity<List<Brand>> findAll() {
        return ResponseEntity.ok().body(brandService.findAll());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id) {
        Optional<Brand> optionalBrand = brandService.findByUuid(id);

        if (optionalBrand.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No brand found with id " + id);
        } else {
            return ResponseEntity.ok().body(optionalBrand.get());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BrandDto brandDto) {
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandDto, brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandService.save(brand));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Optional<Brand> optionalBrand = brandService.findByUuid(id);

        if (optionalBrand.isPresent()) {
            brandService.delete(optionalBrand.get());
            return ResponseEntity.ok().body("Deleted succesfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any brand found with id " + id);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody @Valid BrandDto brandDto) {
        Optional<Brand> optionalBrand = brandService.findByUuid(id);

        if (optionalBrand.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Any brand found with id " + id);
        } else {
            Brand brand = optionalBrand.get();
            BeanUtils.copyProperties(brandDto, brand);
            return ResponseEntity.ok().body(brandService.save(brand));
        }
    }
}
