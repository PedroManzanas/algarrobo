package com.desarrollo.algarrobo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.desarrollo.algarrobo.service.ClienteService;
import com.desarrollo.algarrobo.service.MuebleService;
import com.desarrollo.algarrobo.service.PedidoService;
import com.desarrollo.algarrobo.service.UsuarioService;


@Controller
public class DashboardController {
    
    private final ClienteService clienteService;
    private final MuebleService muebleService;
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    public DashboardController(ClienteService clienteService, MuebleService muebleService, PedidoService pedidoService, UsuarioService usuarioService) {
        this.clienteService = clienteService;
        this.muebleService = muebleService;
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalClientes", clienteService.contarClientes());
        model.addAttribute("totalMuebles", muebleService.contarMuebles());  
        model.addAttribute("totalPedidos", pedidoService.contarPedidos());
        model.addAttribute("facturacionTotal", pedidoService.obtenerFacturacionTotal());
        model.addAttribute("totalUsuarios", usuarioService.contarUsuarios());

        model.addAttribute("pedidosPendientes" , pedidoService.contarPedidosEstados(com.desarrollo.algarrobo.entity.EstadoPedido.PENDIENTE));
        model.addAttribute("pedidosEnFabricacion" , pedidoService.contarPedidosEstados(com.desarrollo.algarrobo.entity.EstadoPedido.EN_FABRICACION));
        model.addAttribute("pedidosListosParaRetirar" , pedidoService.contarPedidosEstados(com.desarrollo.algarrobo.entity.EstadoPedido.LISTO_PARA_RETIRAR));
        model.addAttribute("pedidosRetirados", pedidoService.contarPedidosEstados(com.desarrollo.algarrobo.entity.EstadoPedido.RETIRADO));
        return "dashboard";

    }

}