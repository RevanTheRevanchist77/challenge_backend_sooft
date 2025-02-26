package com.challengebackend.infraestructure.controller;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.EmpresaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @GetMapping("/adheridas-ultimo-mes")
    public ResponseEntity<Page<Empresa>> obtenerEmpresasAdheridasUltimoMes(
            @PageableDefault(size = 10, sort = "fechaAdhesion") Pageable pageable) {
        Page<Empresa> empresas = empresaService.obtenerEmpresasAdheridasUltimoMes(pageable);
        return ResponseEntity.ok(empresas);
    }
    
    

    @PostMapping("/adherir")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adherirEmpresa(@Valid @RequestBody Empresa empresa, BindingResult bindingResult) {
        // hago el manejo de errores de validación
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CUIT inválido");
        }

        // realizao el manejo de excepción de CUIT duplicado
        try {
            empresaService.adherirEmpresa(empresa);
            return ResponseEntity.ok("Empresa adherida correctamente");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CUIT duplicado");
        }
    }
}