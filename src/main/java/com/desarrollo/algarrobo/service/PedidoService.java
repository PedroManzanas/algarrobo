package com.desarrollo.algarrobo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desarrollo.algarrobo.entity.Pedido;
import com.desarrollo.algarrobo.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> buscarPorNombreCliente(String texto) {
    return pedidoRepository.findByClienteNombreContainingIgnoreCaseOrClienteApellidoContainingIgnoreCase(texto, texto);
    }

}