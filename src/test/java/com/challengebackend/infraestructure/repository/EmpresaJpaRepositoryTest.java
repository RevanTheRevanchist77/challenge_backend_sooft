package com.challengebackend.infraestructure.repository;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.domain.Page;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class EmpresaJpaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindByFechaAdhesionAfter() {
        LocalDate fecha = LocalDate.now().minusMonths(1);
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9");
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(fecha);

        empresaRepository.save(empresa);

        Page<Empresa> empresas = empresaRepository.findByFechaAdhesionAfter(
            fecha.minusDays(1), PageRequest.of(0, 10));

        assertEquals(1, empresas.getContent().size());
        assertEquals("30-20389875-9", empresas.getContent().get(0).getCuit());
    }

    @Test
    void testFindByFechaAdhesionAfter_SinEmpresas() {
        LocalDate fecha = LocalDate.now().minusMonths(1);

        Page<Empresa> empresas = empresaRepository.findByFechaAdhesionAfter(fecha, PageRequest.of(0, 10));

        assertEquals(0, empresas.getContent().size()); 
    }
}