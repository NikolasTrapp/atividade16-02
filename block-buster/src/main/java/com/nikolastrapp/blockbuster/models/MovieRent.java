package com.nikolastrapp.blockbuster.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_MOVIE_RENT")
public class MovieRent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 90)
    private String movieTitle;
    @Column(nullable = false, unique = false, length = 90)
    private String movieGender;
    @Column(nullable = false)
    private Double moviePrice;
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

}
