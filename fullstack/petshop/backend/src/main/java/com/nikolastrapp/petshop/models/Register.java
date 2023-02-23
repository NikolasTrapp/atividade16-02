package com.nikolastrapp.petshop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nikolastrapp.petshop.enums.TreatmentType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "TB_REGISTERS")
@Data
public class Register implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Brazil/East")
    @Column(nullable = false)
    private LocalDateTime registerDate;

    @Column(nullable = false)
    private TreatmentType treatmentType;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

}
