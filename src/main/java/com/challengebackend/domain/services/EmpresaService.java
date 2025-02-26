package com.challengebackend.domain.services;

import java.time.LocalDate;
import java.util.List;
import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        LocalDate fechaInicio = LocalDate.now().minusMonths(1);
        return empresaRepository.findByFechaAdhesionAfter(fechaInicio);
    }

    public void adherirEmpresa(Empresa empresa) {
        empresa.setFechaAdhesion(LocalDate.now());
        empresaRepository.save(empresa);
    }
}