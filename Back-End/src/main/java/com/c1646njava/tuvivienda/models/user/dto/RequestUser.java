package com.c1646njava.tuvivienda.models.user.dto;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public record RequestUser(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String name,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico debe ser válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
        String password,

        @NotBlank(message = "La confirmación de contraseña es obligatoria")
        String password2,

        @NotBlank(message = "El país es obligatorio")
        String country,
        ImageUser avatar
) {
}
