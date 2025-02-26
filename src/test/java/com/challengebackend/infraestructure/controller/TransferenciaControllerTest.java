package com.challengebackend.infraestructure.controller;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Test
    void testObtenerTransferUltimoMesPorEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setCuit("30-12345678-9");
        empresa.setRazonSocial("King Gizzard");

        when(transferenciaService.obtenerTransferUltimoMesPorEmpresa()).thenReturn(List.of(empresa));

        ResponseEntity<List<Empresa>> response = transferenciaController.obtenerTransferUltimoMesPorEmpresa();

        assertEquals(1, response.getBody().size());
        assertEquals("30-12345678-9", response.getBody().get(0).getCuit());
    }
}