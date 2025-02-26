package com.challengebackend.domain.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.challengebackend.domain.models.Transferencia;


public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByFechaTransferenciaAfter(LocalDate fechaTransferencia);
}
