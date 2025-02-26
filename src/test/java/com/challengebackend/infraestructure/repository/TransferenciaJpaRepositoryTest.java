package com.challengebackend.infraestructure.repository;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.models.Transferencia;
import com.challengebackend.domain.repository.TransferenciaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TransferenciaJpaRepositoryTest {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindDistinctEmpresaByFechaTransferenciaAfter() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now().minusYears(1));

        entityManager.persist(empresa);
        entityManager.flush();

        Transferencia transferencia = new Transferencia();
        transferencia.setImporte(1000.50);
        transferencia.setCuentaDebito("CUENTADEBITO001");
        transferencia.setCuentaCredito("CUENTACREDITO002");
        transferencia.setFechaTransferencia(fecha);
        transferencia.setEmpresa(empresa);

        entityManager.persist(transferencia);
        entityManager.flush();

        Page<Empresa> empresas = transferenciaRepository.findDistinctEmpresaByFechaTransferenciaAfter(
            fecha.minusDays(1), PageRequest.of(0, 10));

        assertEquals(1, empresas.getContent().size());
        assertEquals("30-20389875-9", empresas.getContent().get(0).getCuit());
    }

    @Test
    void testFindByFechaAfter_SinTransferencias() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        Page<Empresa> empresas = transferenciaRepository.findDistinctEmpresaByFechaTransferenciaAfter(
            fecha, PageRequest.of(0, 10)); // Página 0, tamaño 10

        assertEquals(0, empresas.getContent().size()); // verifico que no hay empresas
    }
}