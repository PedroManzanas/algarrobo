package com.desarrollo.algarrobo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desarrollo.algarrobo.entity.Mueble;

public interface MuebleRepository extends JpaRepository<Mueble, Long> {
    List<Mueble> findByNombreContainingIgnoreCase(String nombre);
}