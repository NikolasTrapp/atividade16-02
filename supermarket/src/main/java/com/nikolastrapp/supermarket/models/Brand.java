package com.nikolastrapp.supermarket.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_BRANDS")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 80, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    List<Product> products = new ArrayList<>();


}
