package com.c1646njava.tuvivienda.models.user.dto;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public record RequestUser(
        @NotBlank(message = "The name is required")
        @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password,

        @NotBlank(message = "Password confirmation is mandatory")
        String password2,

        @NotBlank(message = "Country is required")
        String country,

        ImageUser avatar,

        int advertisingTokens
) {
}
