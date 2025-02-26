package com.challengebackend.domain.repository;

import com.challengebackend.domain.models.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Page<Empresa> findByFechaAdhesionAfter(LocalDate fecha, Pageable pageable);
}