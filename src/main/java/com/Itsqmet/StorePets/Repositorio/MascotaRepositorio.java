package com.Itsqmet.StorePets.Repositorio;

import com.Itsqmet.StorePets.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
}
