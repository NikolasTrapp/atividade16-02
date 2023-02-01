package com.nikolastrapp.desafio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RegistroDto implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private UUID mutanteId;

    private LocalDateTime dataSaida;

}