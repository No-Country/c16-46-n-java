package com.c1646njava.tuvivienda.models.user.dto;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record ResponseUser(
        Long id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String name,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico debe ser válido")
        String email,

        String country,

        ImageUser avatar
) {

    public ResponseUser(User user){
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCountry(),
                user.getAvatar());
    }
}

