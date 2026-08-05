package com.desarrollo.algarrobo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desarrollo.algarrobo.entity.Mueble;
import com.desarrollo.algarrobo.service.MuebleService;

@Controller
public class MuebleController {

    private final MuebleService muebleService;

    @Autowired
    public MuebleController(MuebleService muebleService) {
        this.muebleService = muebleService;
    }

    @GetMapping("/muebles")
    public String listarMuebles(@RequestParam(required = false) String nombre, Model model) {

        if (nombre != null && !nombre.isBlank()) {
            model.addAttribute("muebles", muebleService.buscarPorNombre(nombre));
        } 
        else{
            model.addAttribute("muebles", muebleService.listarMuebles());
        }

        model.addAttribute("nombre", nombre);
        return "muebles/lista";
    }

    @GetMapping("/muebles/nuevo")
    public String nuevoMueble(Model model) {

        model.addAttribute("mueble", new Mueble());
        return "muebles/formulario";
    }

    @PostMapping("/muebles")
    public String guardarMueble(@ModelAttribute Mueble mueble, RedirectAttributes redirectAttributes) {

        boolean esNuevo = (mueble.getId() == null);
        muebleService.guardarMueble(mueble);

        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensaje", "Mueble agregado correctamente.");
        } 
        else {
            redirectAttributes.addFlashAttribute("mensaje", "Mueble actualizado correctamente.");
        }

        return "redirect:/muebles";
    }

    @GetMapping("/muebles/editar/{id}")
    public String editarMueble(@PathVariable Long id, Model model) {

        Mueble mueble = muebleService.buscarPorId(id);
        if (mueble == null) {
            return "redirect:/muebles";
        }

        model.addAttribute("mueble", mueble);
        return "muebles/formulario";
    }

    @PostMapping("/muebles/eliminar/{id}")
    public String eliminarMueble(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            muebleService.eliminarMueble(id);
            redirectAttributes.addFlashAttribute("mensaje", "Mueble eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se puede eliminar el mueble porque tiene pedidos asociados.");
        }

        return "redirect:/muebles";
    }
}