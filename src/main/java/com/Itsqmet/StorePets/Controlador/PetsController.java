package com.Itsqmet.StorePets.Controlador;

import com.Itsqmet.StorePets.Entidad.Carnet;
import com.Itsqmet.StorePets.Entidad.Cliente;
import com.Itsqmet.StorePets.Entidad.Mascota;
import com.Itsqmet.StorePets.Servicio.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {
    @Autowired
    PetsService petsService;

    //Obtener todos los Datos
    @GetMapping("/listaMascotas")
    public List<Mascota> getMascotas(){
        return petsService.getAllPets();
    }


    @GetMapping("/listarClientes")
    public List<Cliente> getClientes(){
        return petsService.getAllClients();
    }

    @GetMapping("/listarCarnet")
    public List<Carnet> getCarnet(){
        return petsService.getAllCarnet();
    }

    //Guardar Todos Los Datos
    @PostMapping("/guardarMascota")
    public String savePet( @RequestBody Mascota mascota){
        petsService.savePet(mascota);

        return "Mascota Guardado";
    }

    @PostMapping("/guardarCarnet")
    public String saveCarnet(@RequestBody Carnet carnet){
     petsService.saveCarnet(carnet);
        return"Carnet Guardado";
    }

    @PostMapping("/guardarCliente")
    public String saveClient(@RequestBody Cliente cliente){
       petsService.saveClient(cliente);
        System.out.println(cliente);
       return "Cliente Guardado";
    }

    //Eliminar Todos Los Datos
    @DeleteMapping("/eliminarMascota/{id}")
    public void deletePet(@PathVariable Long id){
        petsService.deletePet(id);
    }

    @DeleteMapping("/eliminarCarnet/{id}")
    public void deleteCarnet(@PathVariable Long id){
         petsService.deleteCarnet(id);
    }

    @DeleteMapping("/eliminarCliente/{id}")
    public void deleteClient(@PathVariable Long id){
        petsService.deleteClient(id);
    }



}
