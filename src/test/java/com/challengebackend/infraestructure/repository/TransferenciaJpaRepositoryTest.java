package com.challengebackend.infraestructure.repository;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;
import com.challengebackend.domain.repository.TransferenciaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class TransferenciaJpaRepositoryTest {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    void testFindByFechaAfter() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now().minusYears(1));

        // Guarda primero la empresa
        entityManager.persist(empresa);
        entityManager.flush();

        Transferencia transferencia = new Transferencia();
        transferencia.setImporte(1000.50);
        transferencia.setCuentaDebito("CUENTADEBITO001");
        transferencia.setCuentaCredito("CUENTACREDITO002");
        transferencia.setFechaTransferencia(fecha);
        transferencia.setEmpresa(empresa);

        // Guarda la transferencia
        entityManager.persist(transferencia);
        entityManager.flush();

        List<Transferencia> transferencias = transferenciaRepository.findByFechaTransferenciaAfter(fecha.minusDays(1));

        assertEquals(1, transferencias.size());
        assertEquals("30-20389875-9", transferencias.get(0).getEmpresa().getCuit());
    }
}