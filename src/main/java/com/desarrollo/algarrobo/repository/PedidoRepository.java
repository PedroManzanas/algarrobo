package com.desarrollo.algarrobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desarrollo.algarrobo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
