package com.nikolastrapp.petshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolastrapp.petshop.enums.CoatType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PETS")
@Data
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String race;
    @Column(nullable = false)
    private Double heigth;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private CoatType coatType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<Register> registers = new ArrayList<>();

}
