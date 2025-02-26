package com.challengebackend.domain.services;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public Page<Empresa> obtenerEmpresasConTransferenciasUltimoMes(Pageable pageable) {
        LocalDate fechaInicio = LocalDate.now().minusMonths(1);
        return transferenciaRepository.findDistinctEmpresaByFechaTransferenciaAfter(fechaInicio, pageable);
    }
}