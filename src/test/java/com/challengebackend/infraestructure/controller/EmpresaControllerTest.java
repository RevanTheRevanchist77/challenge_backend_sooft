package com.challengebackend.infraestructure.controller;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.EmpresaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EmpresaControllerTest {

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @Test
    void testObtenerEmpresasAdheridasUltimoMes() {
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(fecha);

        when(empresaService.obtenerEmpresasAdheridasUltimoMes()).thenReturn(List.of(empresa));

        ResponseEntity<List<Empresa>> response = empresaController.obtenerEmpresasAdheridasUltimoMes();

        assertEquals(1, response.getBody().size());
        assertEquals("30-20389875-9", response.getBody().get(0).getCuit());
    }
}