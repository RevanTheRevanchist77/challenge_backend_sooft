package com.challengebackend.domain.services;

import java.time.LocalDate;
import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Page<Empresa> obtenerEmpresasAdheridasUltimoMes(Pageable pageable) {
        LocalDate fechaInicio = LocalDate.now().minusMonths(1);
        return empresaRepository.findByFechaAdhesionAfter(fechaInicio, pageable);
    }
    
    
    public void adherirEmpresa(Empresa empresa) {
        try {
            empresaRepository.save(empresa);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("CUIT duplicado");
        }
    }
}