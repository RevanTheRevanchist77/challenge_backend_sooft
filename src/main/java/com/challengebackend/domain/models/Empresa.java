package com.challengebackend.domain.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long id;

    @NotBlank(message = "El CUIT no puede estar vacío")
    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "El CUIT debe tener el formato XX-XXXXXXXX-X")
    @Column(name = "cuit", unique = true, nullable = false)
    private String cuit;

    @NotBlank(message = "La razón social no puede estar vacía")
    @Size(min = 3, max = 100, message = "La razón social debe tener entre 3 y 100 caracteres")
    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @NotNull(message = "La fecha de adhesión no puede ser nula")
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
