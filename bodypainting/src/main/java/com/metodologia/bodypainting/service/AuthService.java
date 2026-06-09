package com.metodologia.bodypainting.service;

import com.metodologia.bodypainting.dto.LoginRequestDTO;
import com.metodologia.bodypainting.dto.LoginResponseDTO;
import com.metodologia.bodypainting.dto.RegistroRequestDTO;
import com.metodologia.bodypainting.dto.UsuarioResponseDTO;
import com.metodologia.bodypainting.exception.EmailAlreadyExistsException;
import com.metodologia.bodypainting.exception.InvalidCredentialsException;
import com.metodologia.bodypainting.model.Usuario;
import com.metodologia.bodypainting.repository.UsuarioRepository;
import com.metodologia.bodypainting.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio encargado del registro, login y consulta del usuario autenticado.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registra un nuevo usuario en el sistema.
     * La contraseña se guarda hasheada con BCrypt.
     */
    @Transactional
    public UsuarioResponseDTO registrarUsuario(RegistroRequestDTO request) {
        String emailNormalizado = normalizarEmail(request.getEmail());

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            throw new EmailAlreadyExistsException(
                    "El email '" + request.getEmail() + "' ya está registrado en el sistema."
            );
        }

        Usuario nuevoUsuario = Usuario.builder()
                .nombre(request.getNombre().trim())
                .apellido(request.getApellido().trim())
                .email(emailNormalizado)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return UsuarioResponseDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .apellido(usuarioGuardado.getApellido())
                .email(usuarioGuardado.getEmail())
                .build();
    }

    /**
     * Valida las credenciales del usuario.
     * Si son correctas, devuelve los datos del usuario y un JWT.
     */
    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO request) {
        String emailNormalizado = normalizarEmail(request.getEmail());

        Usuario usuario = usuarioRepository.findByEmail(emailNormalizado)
                .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

        boolean passwordValida = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        );

        if (!passwordValida) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario);

        return LoginResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .token(token)
                .build();
    }

    /**
     * Devuelve los datos básicos del usuario autenticado.
     * Se usa en el endpoint /me.
     */
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorEmail(String email) {
        String emailNormalizado = normalizarEmail(email);

        Usuario usuario = usuarioRepository.findByEmail(emailNormalizado)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .build();
    }

    /**
     * Normaliza el email para evitar problemas de espacios o mayúsculas.
     */
    private String normalizarEmail(String email) {
        return email.trim().toLowerCase();
    }
}
