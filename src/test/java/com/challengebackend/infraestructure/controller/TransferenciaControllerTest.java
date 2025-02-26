package com.challengebackend.infraestructure.controller;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresa.setCuit("30-12345678-9");
        empresa.setRazonSocial("King Gizzard");
    }

    @Test
    void testObtenerTransferUltimoMesPorEmpresa() {
        when(transferenciaService.obtenerEmpresasConTransferenciasUltimoMes(any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(empresa)));

        ResponseEntity<Page<Empresa>> response = transferenciaController.obtenerEmpresasConTransferenciasUltimoMes(PageRequest.of(0, 10));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals("30-12345678-9", response.getBody().getContent().get(0).getCuit());
    }
}