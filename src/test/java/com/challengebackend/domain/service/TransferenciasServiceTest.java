package com.challengebackend.domain.service;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;
import com.challengebackend.domain.repository.TransferenciaRepository;
import com.challengebackend.domain.services.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TransferenciasServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    private Empresa empresa;
    private Transferencia transferencia;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");

        transferencia = new Transferencia();
        transferencia.setEmpresa(empresa);
    }

    @Test
    void testObtenerTransferUltimoMesPorEmpresa() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        when(transferenciaRepository.findDistinctEmpresaByFechaTransferenciaAfter(eq(fecha), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(empresa)));

        Page<Empresa> empresas = transferenciaService.obtenerEmpresasConTransferenciasUltimoMes(PageRequest.of(0, 10));

        assertEquals(1, empresas.getContent().size());
        assertEquals("30-20389875-9", empresas.getContent().get(0).getCuit());
    }

    @Test
    void testObtenerTransferUltimoMesPorEmpresa_SinTransferencias() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        when(transferenciaRepository.findDistinctEmpresaByFechaTransferenciaAfter(eq(fecha), any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of()));

        Page<Empresa> empresas = transferenciaService.obtenerEmpresasConTransferenciasUltimoMes(PageRequest.of(0, 10));

        assertEquals(0, empresas.getContent().size());
    }
}