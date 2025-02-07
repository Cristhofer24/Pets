package com.Itsqmet.StorePets.Controlador;

import com.Itsqmet.StorePets.Entidad.Carnet;
import com.Itsqmet.StorePets.Entidad.Cliente;
import com.Itsqmet.StorePets.Entidad.Mascota;
import com.Itsqmet.StorePets.Servicio.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
    public String mostrarMascotas(Model model) {
       model.addAttribute("mascota", new Mascota());
        return "pages/mascotas"; // Ruta a la plantilla mascotas.html
    }

    @GetMapping("pages/adopciones")
    public String mostrarAdopciones(Model model) {

        List<Mascota> mascotas = petsService.getAllPets();
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("carnet", new Carnet());

        return "pages/carnet"; // Ruta a la plantilla carnet.html
    }

    @GetMapping("pages/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("clientes", new Cliente());
        model.addAttribute("origin", "RegistroMascota");
        return "pages/clientes"; // Ruta a la plantilla clientes.html
    }
//Guardar Todos Los Datos
    @PostMapping("/clientes/guardar")
    public String guardarCliente(Cliente cliente ,@RequestParam("origin") String origin ,RedirectAttributes redirectAttributes) {

        Optional<Cliente> clienteExistente = petsService.getClientbyCedula(cliente.getCedula());

        if (clienteExistente.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "La cédula del cliente ya existe.");

            // Redirigir de vuelta al formulario de acuerdo con el origen.
            if ("Adoptar".equals(origin)) {
                return "redirect:/" + origin;
            } else if ("RegistroMascota".equals(origin)) {
                return "redirect:/pages/clientes";
            }

        }

        petsService.saveClient(cliente);
        redirectAttributes.addFlashAttribute("success", "Carnet registrado con éxito.");
        if ("Adoptar".equals(origin)) {
            return "redirect:/ListaMascotas?clienteId=" + cliente.getId();  // Redirige a la lista de mascotas
        } else if ("RegistroMascota".equals(origin)) {

            return "redirect:/pages/mascotas";  // Redirige al formulario de registro de mascota
        }
        return "redirect:/";
    }

    @PostMapping("/pages/mascotas")
    public String guardarMascota(Mascota mascota) {
        petsService.savePet(mascota);
        return "redirect:/pages/adopciones";
    }
    //Guardar Carnet
    @PostMapping("/pages/carnet")
    public String guardarCarnet( Carnet carnet , RedirectAttributes redirectAttributes) {
        Carnet carnetExistente = petsService.buscarCarnetPorMascotaId(carnet.getMascota().getId());

        if (carnetExistente != null) {
            // Mascota ya tiene un carnet, añadir un mensaje de error
            redirectAttributes.addFlashAttribute("error", "La mascota ya tiene un carnet asignado.");
            return "redirect:/pages/adopciones"; // Redirige con el mensaje de error
        }

        // Guardar el carnet si no existe
        petsService.saveCarnet(carnet);
        redirectAttributes.addFlashAttribute("success", "Carnet registrado con éxito.");
        return "redirect:/";
    }







}
