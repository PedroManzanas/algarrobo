package com.desarrollo.algarrobo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desarrollo.algarrobo.entity.Rol;
import com.desarrollo.algarrobo.entity.Usuario;
import com.desarrollo.algarrobo.service.UsuarioService;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(@RequestParam(required = false) String nombre, Model model){
        if (nombre != null && !nombre.isBlank()){
            model.addAttribute("usuarios", usuarioService.buscarPorNombre(nombre));
        }
        else{
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
        }
        model.addAttribute("nombre", nombre);
        return "usuarios/lista";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }

    @PostMapping("/usuarios")
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        boolean esNuevo = (usuario.getId() == null);
        usuarioService.guardarUsuario(usuario);
        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensaje", "Usuario añadido correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model){
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", Rol.values());
        return "usuarios/formulario";
    }

    @PostMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes){

        try{usuarioService.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente");
        }
        catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario");
            return "redirect:/usuarios";
        }

        
        return "redirect:/usuarios";
    }
}
