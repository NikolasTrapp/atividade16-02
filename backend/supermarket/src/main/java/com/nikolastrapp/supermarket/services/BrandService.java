package com.nikolastrapp.supermarket.services;

import com.nikolastrapp.supermarket.models.Brand;
import com.nikolastrapp.supermarket.repositories.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public List<Brand> queryAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findByUuid(UUID id) {
        return brandRepository.findById(id);
    }

    @Transactional
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public List<Brand> insertAll(List<Brand> categories) {
        return brandRepository.saveAll(categories);
    }

    @Transactional
    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }


}
