package com.desarrollo.algarrobo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desarrollo.algarrobo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteNombreContainingIgnoreCaseOrClienteApellidoContainingIgnoreCase(String nombre, String apellido); 
}
