package com.metodologia.bodypainting.controller;

import com.metodologia.bodypainting.dto.RegistroRequestDTO;
import com.metodologia.bodypainting.dto.UsuarioResponseDTO;
import com.metodologia.bodypainting.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint para registrar un nuevo usuario en la plataforma.
     *
     * @param request DTO con los datos del usuario.
     * @return ResponseEntity con el DTO del usuario registrado y el código de estado 201 CREATED.
     */
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody RegistroRequestDTO request) {
        UsuarioResponseDTO response = authService.registrarUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
