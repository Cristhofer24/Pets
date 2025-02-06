package com.Itsqmet.StorePets.Controlador;

import com.Itsqmet.StorePets.Entidad.Cliente;
import com.Itsqmet.StorePets.Servicio.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    PetsService petsService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("mensaje", "Bienvenido al sistema de adopciones de mascotas");
        return "index"; // Devuelve la vista index.html
    }



    @GetMapping("pages/mascotas")
    public String mostrarMascotas() {
        return "pages/mascotas"; // Ruta a la plantilla mascotas.html
    }
    @GetMapping("pages/adopciones")
    public String mostrarAdopciones() {
        return "pages/adopcion"; // Ruta a la plantilla adopcion.html
    }




    @GetMapping("pages/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", new Cliente());
        return "pages/clientes"; // Ruta a la plantilla clientes.html
    }

    @PostMapping("/pages/clientes")
    public String guardarCliente(Cliente cliente) {
        petsService.saveClient(cliente);
        return "redirect:/pages/clientes";
    }
}
