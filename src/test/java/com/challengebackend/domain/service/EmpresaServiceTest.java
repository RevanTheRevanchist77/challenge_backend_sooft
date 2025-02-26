package com.challengebackend.domain.service;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;
import com.challengebackend.domain.services.EmpresaService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    void testObtenerEmpresasAdheridasUltimoMes() {
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(fecha);

        when(empresaRepository.findByFechaAdhesionAfter(eq(fecha), any(Pageable.class)))
        .thenReturn(new PageImpl<>(List.of(empresa)));

        Page<Empresa> empresas = empresaService.obtenerEmpresasAdheridasUltimoMes(PageRequest.of(0, 10));

        assertEquals(1, empresas.getContent().size());
        assertEquals("30-20389875-9", empresas.getContent().get(0).getCuit());
    }
}
