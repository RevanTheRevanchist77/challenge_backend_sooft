package com.challengebackend.infraestructure.controller;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/adheridas-ultimo-mes")
    public ResponseEntity<List<Empresa>> obtenerEmpresasAdheridasUltimoMes() {
        List<Empresa> empresas = empresaService.obtenerEmpresasAdheridasUltimoMes();
        return ResponseEntity.ok(empresas); // Envuelve la lista en un ResponseEntity
    }
    
    

    @PostMapping("/adherir")
    public void adherirEmpresa(@RequestBody Empresa empresa) {
        empresaService.adherirEmpresa(empresa);
    }
}