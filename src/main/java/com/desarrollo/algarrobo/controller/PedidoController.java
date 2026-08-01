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

import com.desarrollo.algarrobo.entity.EstadoPedido;
import com.desarrollo.algarrobo.entity.Pedido;
import com.desarrollo.algarrobo.service.ClienteService;
import com.desarrollo.algarrobo.service.MuebleService;
import com.desarrollo.algarrobo.service.PedidoService;

@Controller
public class PedidoController {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final MuebleService muebleService;

@Autowired
public PedidoController(PedidoService pedidoService, ClienteService clienteService, MuebleService muebleService) {
    this.pedidoService = pedidoService;
    this.clienteService = clienteService;
    this.muebleService = muebleService;
    }

    @GetMapping("/pedidos")
    public String listarPedidos(@RequestParam(required = false) String nombre, Model model) {

    if (nombre != null && !nombre.isBlank()) {
        model.addAttribute("pedidos", pedidoService.buscarPorNombreCliente(nombre));
    } 
    else{
        model.addAttribute("pedidos", pedidoService.listarPedidos());
    }

    model.addAttribute("nombre", nombre);

    return "pedidos/lista";
}

    @GetMapping("/pedidos/nuevo")
    public String nuevoPedido(Model model) {

    model.addAttribute("pedido", new Pedido());
    model.addAttribute("clientes", clienteService.listarClientes());
    model.addAttribute("muebles", muebleService.listarMuebles());
    model.addAttribute("estados", EstadoPedido.values());

    return "pedidos/formulario";
    }

    @PostMapping("/pedidos")
    public String guardarPedido(@ModelAttribute Pedido pedido, RedirectAttributes redirectAttributes) {
        boolean esNuevo = (pedido.getId() == null);
    /*  if (pedido.getFechaSolicitud() == null){
            pedido.setFechaSolicitud(LocalDate.now());
        }
    */
        pedidoService.guardarPedido(pedido);
        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensaje", "Pedido creado correctamente");
        } 
        else{
            redirectAttributes.addFlashAttribute("mensaje", "Pedido actualizado correctamente");
        }
    System.out.println("-------------------------------AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA--------");
    System.out.println(pedido.getFechaSolicitud());
    System.out.println(pedido.getFechaEntregaEstimada());
    return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editarPedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null)
            return "redirect:/pedidos";
        
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("muebles", muebleService.listarMuebles());
        model.addAttribute("estados", EstadoPedido.values());
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Fecha solicitud: " + pedido.getFechaSolicitud());
        System.out.println("Fecha entrega: " + pedido.getFechaEntregaEstimada());

        return "pedidos/formulario";
    }

    @GetMapping("/pedidos/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        
        pedidoService.eliminarPedido(id);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido elminado correctamente");

        return "redirect:/pedidos";
    }

}
