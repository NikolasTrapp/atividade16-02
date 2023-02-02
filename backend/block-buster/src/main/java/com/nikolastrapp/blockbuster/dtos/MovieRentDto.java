package com.nikolastrapp.blockbuster.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class MovieRentDto {
    @NotBlank
    private final String movieTitle;
    @NotBlank
    private final String movieGender;
    @NotNull
    private final Double moviePrice;

    @NotNull
    private final UUID customerId;
}