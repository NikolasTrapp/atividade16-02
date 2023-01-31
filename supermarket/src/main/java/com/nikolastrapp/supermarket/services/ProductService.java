package com.nikolastrapp.supermarket.services;

import com.nikolastrapp.supermarket.models.Product;
import com.nikolastrapp.supermarket.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> queryAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByUuid(UUID id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> insertAll(List<Product> categories) {
        return productRepository.saveAll(categories);
    }

    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
