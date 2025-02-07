package com.Itsqmet.StorePets.Controlador;

import com.Itsqmet.StorePets.Entidad.Carnet;
import com.Itsqmet.StorePets.Entidad.Cliente;
import com.Itsqmet.StorePets.Entidad.Mascota;
import com.Itsqmet.StorePets.Servicio.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class Adopciones {

    @Autowired
    PetsService petsService;
    @GetMapping("/Adoptar")
    public String Adoptar(Model model){
        model.addAttribute("origin", "Adoptar");
        model.addAttribute("clientes", new Cliente());
        return "pages/clientes";
    }

    @GetMapping("/ListaMascotas")
    public String listarMascotas(@RequestParam("clienteId") Long clienteId, Model model) {
        List<Mascota> mascotas = petsService.getAllPets();
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("clienteId", clienteId);
        return "pages/listaMascotas";
    }


    //Adoptar

    @GetMapping("/adoptar/{mascotaId}")
    public String adoptar(@PathVariable Long mascotaId, @RequestParam("clienteId") Long clienteId, Model model) {
        if (clienteId == null) {
            return "redirect:/listamascotas?error=ClienteIdFaltante";
        }
        Optional<Mascota> mascota = petsService.getPetbyId(mascotaId);
        Optional<Cliente> cliente = petsService.getClientbyId(clienteId);

        if (mascota.isPresent() && cliente.isPresent()) {
            model.addAttribute("mascota", mascota.get());
            model.addAttribute("cliente", cliente.get());
            return "pages/confirmarAdopcion";  // Página adopcion.html
        }

        return "redirect:/listamascotas";
    }

    @GetMapping("/ListClientes")
    public String ListClientes(Model model){
        List<Cliente> clientes = petsService.getAllClients();
        model.addAttribute("clientes", clientes);
        return "pages/ListaClientes";
    }

    @GetMapping("/ListMascotas")
    public String ListMascotas(Model model){
        List<Mascota> mascotas = petsService.getAllPets();
        model.addAttribute("mascota", mascotas);
        return "pages/ListMascotas";
    }

    @GetMapping("/ListCarnet")
    public String ListCarnet(Model model){
        List<Carnet> carnet = petsService.getAllCarnet();
        model.addAttribute("carnet", carnet);
        return "pages/ListCarnet";
    }

    @GetMapping("/Admin")
    public String Admin(){
        return "pages/Admin";
    }


    @PostMapping("/adoptar/guardar")
    public String guardarAdopcion(@RequestParam(value = "mascotaId", required = true) Long mascotaId,
                                  @RequestParam(value = "clienteId", required = true) Long clienteId) {
        Optional<Mascota> mascotaOpt = petsService.getPetbyId(mascotaId);
        Optional<Cliente> clienteOpt = petsService.getClientbyId(clienteId);

        if (mascotaOpt.isPresent() && clienteOpt.isPresent()) {
            Mascota mascota = mascotaOpt.get();
            Cliente cliente = clienteOpt.get();

            // Asignar el cliente a la mascota
            mascota.setCliente(cliente);
            petsService.savePet(mascota);

            return "redirect:/";
        }

        System.out.println("Mascota o Cliente no encontrados.");
        return "redirect:/";
    }



}
