package com.desarrollo.algarrobo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.desarrollo.algarrobo.entity.Cliente;
import com.desarrollo.algarrobo.repository.ClienteRepository;

@Service
public class ClienteService {
     private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null){
            clienteRepository.delete(cliente);
        }
    }

    public List<Cliente> buscarPorNombre(String nombre){
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

}