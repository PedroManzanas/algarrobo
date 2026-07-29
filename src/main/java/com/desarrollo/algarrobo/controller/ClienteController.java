package com.desarrollo.algarrobo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.desarrollo.algarrobo.entity.Cliente;
import com.desarrollo.algarrobo.service.ClienteService;

@Controller
public class ClienteController {
    
    // por si acaso hago el autowired si termino agregando mas constructores
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String listarClientes(Model model){

        model.addAttribute("clientes", clienteService.listarClientes());

        return "clientes/lista";
    }
  
    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

}