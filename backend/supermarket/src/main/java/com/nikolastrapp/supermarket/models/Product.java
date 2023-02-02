package com.nikolastrapp.supermarket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "TB_PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private Double price;
    private String category;
    private String barCode;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

}
