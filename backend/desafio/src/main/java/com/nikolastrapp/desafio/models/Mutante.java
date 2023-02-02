package com.nikolastrapp.desafio.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nikolastrapp.desafio.models.enums.Especie;
import com.nikolastrapp.desafio.models.enums.NivelPoder;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="TB_MUTANTES")
public class Mutante implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 255)
    private String name;

    @Column(nullable = false, unique = false, length = 16)
    private Especie especie;

    @Column(nullable = false, unique = false)
    private Integer idade;

    @Column(nullable = false, unique = false, length = 50)
    private String poder;

    @Column(nullable = false, unique = false, length = 50)
    private NivelPoder nivelPoder;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    private boolean dentro = false;

    @OneToMany(mappedBy = "mutante")
    @JsonManagedReference
    List<Registro> registros = new ArrayList<>();

    public boolean estaDentro() {
        return this.dentro;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }
}