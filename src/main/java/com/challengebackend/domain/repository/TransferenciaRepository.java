package com.challengebackend.domain.repository;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query("SELECT DISTINCT t.empresa FROM Transferencia t WHERE t.fechaTransferencia > :fechaInicio")
    Page<Empresa> findDistinctEmpresaByFechaTransferenciaAfter(LocalDate fechaInicio, Pageable pageable);
}