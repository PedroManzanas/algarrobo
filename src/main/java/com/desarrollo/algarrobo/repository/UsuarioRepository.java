package com.desarrollo.algarrobo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desarrollo.algarrobo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);
    Optional<Usuario> findByUsername(String username);
}
