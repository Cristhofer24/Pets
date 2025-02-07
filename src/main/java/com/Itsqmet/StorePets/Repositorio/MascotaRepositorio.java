package com.Itsqmet.StorePets.Repositorio;

import com.Itsqmet.StorePets.Entidad.Carnet;
import com.Itsqmet.StorePets.Entidad.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, Long> {
@Query(value = "select ca.id,ca.fecha_vacunas,ca.fecha_certificado ,ma.nombre,ma.raza,\n" +
        "ma.edad from mascota ma\n" +
        "join carnet ca\n" +
        "on ca.mascota_id=ma.id",nativeQuery = true)
    public List<Mascota> findconsulta();
}
