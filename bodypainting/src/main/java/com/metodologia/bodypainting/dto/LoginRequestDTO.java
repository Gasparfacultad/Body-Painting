package com.metodologia.bodypainting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para recibir los datos de inicio de sesión.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    /**
     * Correo electrónico del usuario.
     */
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;

    /**
     * Contraseña ingresada por el usuario.
     */
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}