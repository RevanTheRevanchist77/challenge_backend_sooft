package com.challengebackend.domain.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transferencia")



public class Transferencia {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transferencia")
    private Long id;

    @Column(name = "importe", nullable = false)
    private Double importe;

    @Column(name = "cuenta_debito", nullable = false)
    private String cuentaDebito;

    @Column(name = "cuenta_credito", nullable = false)
    private String cuentaCredito;

    @Column(name = "fecha_transferencia", nullable = false)
    private LocalDate fechaTransferencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

    
    //Genero manualmente los getters y setters que necesito ya que tuve problemas con la dependencia  de lombok
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getCuentaDebito() {
		return cuentaDebito;
	}

	public void setCuentaDebito(String cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	public String getCuentaCredito() {
		return cuentaCredito;
	}

	public void setCuentaCredito(String cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	public LocalDate getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(LocalDate fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}	

    

    
}
