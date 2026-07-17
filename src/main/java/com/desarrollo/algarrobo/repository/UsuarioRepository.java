package com.desarrollo.algarrobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desarrollo.algarrobo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
