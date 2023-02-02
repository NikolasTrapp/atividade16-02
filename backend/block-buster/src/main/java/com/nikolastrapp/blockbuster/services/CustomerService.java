package com.nikolastrapp.blockbuster.services;

import com.nikolastrapp.blockbuster.models.Customer;
import com.nikolastrapp.blockbuster.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(UUID id){
        return customerRepository.findById(id);
    }

    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(UUID id){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()){
            throw new EntityNotFoundException("No entity found with id: " + id);
        } else {
            customerRepository.delete(customerOptional.get());
        }
    }

    @Transactional
    public Customer update (UUID id, Customer customer){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()){
            throw new EntityNotFoundException("No entity found with id: " + id);
        } else {
            Customer oldCustomer = customerOptional.get();
            updateEntity(oldCustomer, customer);
            return customerRepository.save(oldCustomer);
        }
    }

    public void updateEntity(Customer customer, Customer newEntity){
        customer.setName(newEntity.getName());
        customer.setAddress(newEntity.getAddress());
        customer.setCpf(newEntity.getCpf());
        customer.setEmail(newEntity.getEmail());
        customer.setMovieRents(newEntity.getMovieRents());
    }

}
