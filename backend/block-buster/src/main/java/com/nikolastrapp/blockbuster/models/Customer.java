package com.nikolastrapp.blockbuster.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString
@Entity
@Table(name = "TB_CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 130)
    private String name;
    @Column(nullable = false, unique = true)
    @CPF
    private String cpf;
    @Column(nullable = false, unique = true, length = 255)
    @Email
    private String email;
    @Column(nullable = false, length = 300)
    private String address;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<MovieRent> movieRents = new ArrayList<>();

}
