package com.challengebackend.infraestructure.controller;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/empresas-ultimo-mes")
    public ResponseEntity<Page<Empresa>> obtenerEmpresasConTransferenciasUltimoMes(
            @PageableDefault(size = 10, sort = "fechaTransferencia") Pageable pageable) {
        Page<Empresa> empresas = transferenciaService.obtenerEmpresasConTransferenciasUltimoMes(pageable);
        return ResponseEntity.ok(empresas);
    }
    
}    