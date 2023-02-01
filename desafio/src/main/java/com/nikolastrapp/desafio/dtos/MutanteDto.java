package com.nikolastrapp.desafio.dtos;

import com.nikolastrapp.desafio.models.enums.Especie;
import com.nikolastrapp.desafio.models.enums.NivelPoder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MutanteDto {

    @NotBlank
    private String name;
    @NotBlank
    private Especie especie;
    @NotNull
    private Integer idade;
    @NotBlank
    private String poder;
    @NotBlank
    private NivelPoder nivelPoder;
    @NotBlank
    private String email;

}
