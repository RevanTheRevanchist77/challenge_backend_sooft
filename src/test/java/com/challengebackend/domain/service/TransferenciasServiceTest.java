package com.challengebackend.domain.service;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;
import com.challengebackend.domain.repository.TransferenciaRepository;
import com.challengebackend.domain.services.TransferenciaService;

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
public class TransferenciasServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Test
    void testObtenerTransferUltimoMesPorEmpresa() {
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");

        Transferencia transferencia = new Transferencia();
        transferencia.setEmpresa(empresa);

        when(transferenciaRepository.findByFechaTransferenciaAfter(fecha)).thenReturn(List.of(transferencia));

        List<Empresa> empresas = transferenciaService.obtenerTransferUltimoMesPorEmpresa();

        assertEquals(1, empresas.size());
        assertEquals("30-20389875-9", empresas.get(0).getCuit()); 
        assertEquals("King Gizzard", empresas.get(0).getRazonSocial());

    }
}