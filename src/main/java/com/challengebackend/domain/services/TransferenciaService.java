package com.challengebackend.domain.services;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;
import com.challengebackend.domain.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public List<Empresa> obtenerTransferUltimoMesPorEmpresa() {
        LocalDate fechaInicio = LocalDate.now().minusMonths(1);
        List<Transferencia> transferencias = transferenciaRepository.findByFechaTransferenciaAfter(fechaInicio);
        return transferencias.stream()
                .map(Transferencia::getEmpresa)
                .distinct()
                .collect(Collectors.toList());
    }
}