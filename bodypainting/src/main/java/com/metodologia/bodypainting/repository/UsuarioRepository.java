package com.metodologia.bodypainting.repository;

import com.metodologia.bodypainting.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Verifica si ya existe un usuario registrado con el correo electrónico dado.
     *
     * @param email Correo electrónico a comprobar
     * @return true si ya existe, false en caso contrario
     */
    boolean existsByEmail(String email);

    /**
     * Busca un usuario por email.
     * Será utilizado posteriormente para Login y JWT.
     */
    Optional<Usuario> findByEmail(String email);
}
