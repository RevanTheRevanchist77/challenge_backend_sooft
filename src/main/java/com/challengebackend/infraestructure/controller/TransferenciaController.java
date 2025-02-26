package com.challengebackend.infraestructure.controller;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/empresas-ultimo-mes")
    public ResponseEntity<List<Empresa>> obtenerTransferUltimoMesPorEmpresa() {
        List<Empresa> empresas = transferenciaService.obtenerTransferUltimoMesPorEmpresa();
        return ResponseEntity.ok(empresas); // Envuelve la lista en un ResponseEntity
    }
    
}    