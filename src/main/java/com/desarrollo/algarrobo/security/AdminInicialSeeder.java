package com.desarrollo.algarrobo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.desarrollo.algarrobo.entity.Rol;
import com.desarrollo.algarrobo.entity.Usuario;
import com.desarrollo.algarrobo.repository.UsuarioRepository;

@Component
public class AdminInicialSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInicialSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellido("Inicial");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            usuarioRepository.save(admin);

            System.out.println("=================================================");
            System.out.println("Usuario Admin inicial creado -> usuario: admin / contraseña: admin123");
            System.out.println("Cambiala o crea un usuario nuevo y borra este");
            System.out.println("=================================================");
        }
    }
}