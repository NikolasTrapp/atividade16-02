package com.nikolastrapp.desafio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "TB_REGISTROS")
public class Registro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "mutante_id", referencedColumnName = "id")
    private Mutante mutante;

}