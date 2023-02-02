package com.nikolastrapp.blockbuster.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;


@Data
public class CustomerDto {

    @NotBlank
    private final String name;
    @NotBlank
    @CPF
    private final String cpf;
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    private final String address;
}