package com.Itsqmet.StorePets.Repositorio;

import com.Itsqmet.StorePets.Entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {
public Optional<Cliente> getClienteByCedula(String cedula);
}
