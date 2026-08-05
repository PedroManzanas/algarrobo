package com.desarrollo.algarrobo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desarrollo.algarrobo.entity.EstadoPedido;
import com.desarrollo.algarrobo.entity.Mueble;
import com.desarrollo.algarrobo.entity.Pedido;
import com.desarrollo.algarrobo.repository.MuebleRepository;
import com.desarrollo.algarrobo.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MuebleRepository muebleRepository;

    public PedidoService(PedidoRepository pedidoRepository, MuebleRepository muebleRepository) {
        this.pedidoRepository = pedidoRepository;
        this.muebleRepository = muebleRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Pedido pedido) {
        Mueble mueble = muebleRepository.findById(pedido.getMueble().getId()).orElseThrow();
        pedido.setMueble(mueble);
        pedido.setPrecioFinal(mueble.getPrecio()*pedido.getCantidad());

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

    public long contarPedidos() {
        return pedidoRepository.count();
    }

    public long contarPedidosEstados(EstadoPedido estado) {
        return pedidoRepository.countByEstado(estado);
    }

    public Double obtenerFacturacionTotal() {
        return pedidoRepository.obtenerFacturacionTotal();
    }  
}