package com.Itsqmet.StorePets.Servicio;

import com.Itsqmet.StorePets.Entidad.Carnet;
import com.Itsqmet.StorePets.Entidad.Cliente;
import com.Itsqmet.StorePets.Entidad.Mascota;
import com.Itsqmet.StorePets.Repositorio.CarnetRepositorio;
import com.Itsqmet.StorePets.Repositorio.ClienteRepositorio;
import com.Itsqmet.StorePets.Repositorio.MascotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {
    @Autowired
    private MascotaRepositorio mascotaRepositorio;
    @Autowired
    private CarnetRepositorio carnetRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    //Obtener todas las mascotas
    public List<Mascota> getAllPets(){
        return mascotaRepositorio.findAll();
    }

    //Guardar mascota
    public void savePet(Mascota mascota){
        if (mascota.getCliente() != null && mascota.getCliente().getId() != null) {
            Cliente cliente = clienteRepositorio.findById(mascota.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("El Cliente no existe, o no a sido encontrado"));
            mascota.setCliente(cliente);
        }

        mascotaRepositorio.save(mascota);
    }

    //Eliminar mascota
    public void deletePet(Long id){
        mascotaRepositorio.deleteById(id);
    }



    //obtener Todos los clientes
    public List<Cliente> getAllClients(){
        return clienteRepositorio.findAll();
    }

    //Guardar cliente
    public void saveClient(Cliente cliente) {
     clienteRepositorio.save(cliente);
    }

    //Eliminar cliente
    public void deleteClient(Long id){
        clienteRepositorio.deleteById(id);
    }

    //Obtener todos los carnet
    public List<Carnet> getAllCarnet(){
        return carnetRepositorio.findAll();
    }

    //Guardar carnet
    public void saveCarnet(Carnet carnet){
            if (carnet.getMascota() != null && carnet.getMascota().getId() != null) {
                Mascota mascota = mascotaRepositorio.findById(carnet.getMascota().getId())
                        .orElseThrow(() -> new RuntimeException("La Mascota no existe, o no a sido Registrada"));
                carnet.setMascota(mascota);
            }

        carnetRepositorio.save(carnet);
    }

    //Eliminar carnet
    public void deleteCarnet(Long id){
        carnetRepositorio.deleteById(id);
    }



}
