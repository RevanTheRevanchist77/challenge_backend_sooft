package com.challengebackend.infraestructure.repository;


import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.repository.EmpresaRepository;
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

        List<Empresa> empresas = empresaRepository.findByFechaAdhesionAfter(fecha.minusDays(1));

        assertEquals(1, empresas.size());
        assertEquals("30-20389875-9", empresas.get(0).getCuit()); 
        assertEquals("King Gizzard", empresas.get(0).getRazonSocial());
    }
}