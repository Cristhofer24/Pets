package com.Itsqmet.StorePets.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Carnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCertificado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVacunas;

    //Relacion uno a uno desde Mascotas
    @OneToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCertificado() {
        return fechaCertificado;
    }

    public void setFechaCertificado(LocalDate fechaCertificado) {
        this.fechaCertificado = fechaCertificado;
    }

    public LocalDate getFechaVacunas() {
        return fechaVacunas;
    }

    public void setFechaVacunas(LocalDate fechaVacunas) {
        this.fechaVacunas = fechaVacunas;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }



}
