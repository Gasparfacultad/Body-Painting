package com.metodologia.bodypainting.controller;

import com.metodologia.bodypainting.dto.LoginRequestDTO;
import com.metodologia.bodypainting.dto.LoginResponseDTO;
import com.metodologia.bodypainting.dto.RegistroRequestDTO;
import com.metodologia.bodypainting.dto.UsuarioResponseDTO;
import com.metodologia.bodypainting.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para registro, login y consulta del usuario autenticado.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Registra un nuevo usuario.
     */
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody RegistroRequestDTO request) {
        UsuarioResponseDTO response = authService.registrarUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Inicia sesión y devuelve un token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Devuelve los datos del usuario autenticado.
     * Requiere enviar el token en el header Authorization.
     */
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> me(Authentication authentication) {
        String email = authentication.getName();
        UsuarioResponseDTO response = authService.obtenerUsuarioPorEmail(email);
        return ResponseEntity.ok(response);
    }
}