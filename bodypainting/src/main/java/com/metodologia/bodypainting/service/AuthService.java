package com.metodologia.bodypainting.service;

import com.metodologia.bodypainting.dto.RegistroRequestDTO;
import com.metodologia.bodypainting.dto.UsuarioResponseDTO;
import com.metodologia.bodypainting.exception.EmailAlreadyExistsException;
import com.metodologia.bodypainting.model.Usuario;
import com.metodologia.bodypainting.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request Datos del registro del usuario.
     * @return DTO con la información del usuario registrado.
     * @throws EmailAlreadyExistsException Si el email ya está en uso.
     */
    @Transactional
    public UsuarioResponseDTO registrarUsuario(RegistroRequestDTO request) {
        // Caso Negativo 1: Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("El email '" + request.getEmail() + "' ya está registrado en el sistema.");
        }

        // Crear la entidad Usuario
        // NOTA: En un entorno productivo real, la contraseña debe ser encriptada/hasheada
        // (por ejemplo, con BCryptPasswordEncoder) antes de ser guardada.
        Usuario nuevoUsuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        // Guardar en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // Mapear y retornar la respuesta
        return UsuarioResponseDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .apellido(usuarioGuardado.getApellido())
                .email(usuarioGuardado.getEmail())
                .build();
    }
}
