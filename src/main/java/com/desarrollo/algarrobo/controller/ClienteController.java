package com.desarrollo.algarrobo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desarrollo.algarrobo.entity.Cliente;
import com.desarrollo.algarrobo.service.ClienteService;


@Controller
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public String listarClientes(@RequestParam(required = false) String nombre, Model model){

        if (nombre != null && !nombre.isBlank()){
            model.addAttribute("clientes", clienteService.buscarPorNombre(nombre));
        }
        else{
        model.addAttribute("clientes", clienteService.listarClientes());
        }
        model.addAttribute("nombre", nombre);

        return "clientes/lista";
    }

    @GetMapping("/clientes/nuevo") 
    public String nuevoCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }
  
    @PostMapping("/clientes")
    public String guardarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        boolean esNuevo = (cliente.getId() == null);
        clienteService.guardarCliente(cliente);
        if (esNuevo) {
          redirectAttributes.addFlashAttribute("mensaje", "Cliente añadido correctamente");
        }
        else{
            redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado correctamente");
        }
        
        return "redirect:/clientes";
    }


    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {

        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null)
            return "redirect:/clientes";
        model.addAttribute("cliente", cliente);

        return "clientes/formulario";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        
        clienteService.eliminarCliente(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente elminado Correctamente");

        return "redirect:/clientes";
    }
    


}