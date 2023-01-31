package com.nikolastrapp.blockbuster.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class MailDto implements Serializable {
    @NotBlank
    @Email
    private final String emailFrom;
    @NotBlank
    @Email
    private final String emailTo;
    @NotBlank
    private final String subject;
    @NotBlank
    private final String text;
}