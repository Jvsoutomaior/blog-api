package dev.jvsmaior.blog_api.dto.security;

import jakarta.validation.constraints.NotBlank;

public record RegistrationRequestDTO(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
