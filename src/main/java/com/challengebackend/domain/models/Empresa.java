package com.challengebackend.domain.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long id;

    @Column(name = "cuit", unique = true, nullable = false)
    private String cuit;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Column(name = "fecha_adhesion", nullable = false)
    private LocalDate fechaAdhesion;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transferencia> transferencias = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public LocalDate getFechaAdhesion() {
        return fechaAdhesion;
    }

    public void setFechaAdhesion(LocalDate fechaAdhesion) {
        this.fechaAdhesion = fechaAdhesion;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<Transferencia> transferencias) {
        this.transferencias = transferencias;
    }
}
