package com.nikolastrapp.supermarket.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;


@Data
public class BrandDto implements Serializable {

    @NotBlank
    private final String name;
}