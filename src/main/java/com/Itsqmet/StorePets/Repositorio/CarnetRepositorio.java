package com.Itsqmet.StorePets.Repositorio;

import com.Itsqmet.StorePets.Entidad.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarnetRepositorio extends JpaRepository<Carnet, Long> {
}
