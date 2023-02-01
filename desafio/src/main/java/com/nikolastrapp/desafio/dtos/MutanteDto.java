package com.nikolastrapp.desafio.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MutanteDto {

    @NotBlank
    private String name;

}
