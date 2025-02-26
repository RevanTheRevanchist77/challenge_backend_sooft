package com.challengebackend.domain.service;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;
import com.challengebackend.domain.services.EmpresaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    @Test

    void testObtenerEmpresasAdheridasUltimoMes() {
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(fecha);

        when(empresaRepository.findByFechaAdhesionAfter(fecha)).thenReturn(List.of(empresa));

        List<Empresa> empresas = empresaService.obtenerEmpresasAdheridasUltimoMes();

        assertEquals(1, empresas.size());
        assertEquals("30-20389875-9", empresas.get(0).getCuit());
        assertEquals("King Gizzard", empresas.get(0).getRazonSocial());
    }
}
