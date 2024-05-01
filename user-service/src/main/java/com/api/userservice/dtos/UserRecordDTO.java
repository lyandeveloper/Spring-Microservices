package com.api.userservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDTO(@NotBlank(message = "Nome não pode estar vazio!")
                            String name,
                            @NotBlank(message = "Email não pode estar vazio!")
                            @Email(message = "Formato de email inválido!")
                            String email
) {
}
