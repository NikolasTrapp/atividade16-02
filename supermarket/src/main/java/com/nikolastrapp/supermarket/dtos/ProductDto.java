package com.nikolastrapp.supermarket.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Data
public class ProductDto implements Serializable {
    @NotBlank
    private final String name;
    @NotNull
    private final Double price;
    @NotBlank
    private final String category;
    @NotBlank
    private final String barCode;
    @NotNull
    private final UUID brandId;
}