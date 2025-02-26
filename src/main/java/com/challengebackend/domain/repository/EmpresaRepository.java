package com.challengebackend.domain.repository;

import com.challengebackend.domain.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByFechaAdhesionAfter(LocalDate fecha);
}
	
