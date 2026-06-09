package com.metodologia.bodypainting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Respuesta del login.
 * Devuelve los datos básicos del usuario y el token JWT.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String token;

    @Builder.Default
    private String tokenType = "Bearer";
}