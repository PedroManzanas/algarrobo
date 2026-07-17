package com.desarrollo.algarrobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desarrollo.algarrobo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
