package com.desarrollo.algarrobo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desarrollo.algarrobo.entity.EstadoPedido;
import com.desarrollo.algarrobo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteNombreContainingIgnoreCaseOrClienteApellidoContainingIgnoreCase(String nombre, String apellido); 
    long countByEstado(EstadoPedido estado);
    long count();

    @Query("""
    SELECT COALESCE(SUM(p.precioFinal),0) FROM Pedido p 
    """) Double obtenerFacturacionTotal();
}
