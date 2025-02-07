package com.Itsqmet.StorePets.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mensaje", "Bienvenido al sistema de adopciones de mascotas");
        return "index"; // Devuelve la vista index.html
    }
    @RequestMapping("pages/mascotas")
    public String mostrarMascotas() {
        return "pages/mascotas"; // Ruta a la plantilla mascotas.html
    }

    @GetMapping("pages/carnet")
    public String mostrarAdopciones() {
        return "pages/carnet"; // Ruta a la plantilla carnet.html
    }
    @GetMapping("pages/clientes")
    public String mostrarClientes() {
        return "pages/clientes"; // Ruta a la plantilla clientes.html
    }

}
