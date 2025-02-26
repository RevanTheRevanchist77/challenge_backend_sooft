package com.challengebackend.infraestructure.controller;

import com.challengebackend.domain.models.Empresa;
import com.challengebackend.domain.services.EmpresaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;



import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean 
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    // Elimina la inicialización de "empresa" en setUp()
    // private Empresa empresa;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testObtenerEmpresasAdheridasUltimoMes() throws Exception {
        // Configura los datos de prueba
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9"); // CUIT válido
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now());

        // Simula el comportamiento del servicio
        when(empresaService.obtenerEmpresasAdheridasUltimoMes(any(Pageable.class)))
            .thenReturn(new PageImpl<>(List.of(empresa)));

        // Realiza la solicitud GET
        mockMvc.perform(MockMvcRequestBuilders.get("/empresas/adheridas-ultimo-mes")
                .header("X-API-KEY", "mi-api-key-secreta")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].cuit").value("30-20389875-9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].razonSocial").value("King Gizzard"));

        // Verifica que el servicio se haya llamado
        verify(empresaService, times(1)).obtenerEmpresasAdheridasUltimoMes(any(Pageable.class));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdherirEmpresa_CuitInvalido() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setCuit("123"); // CUIT inválido
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now());

        mockMvc.perform(MockMvcRequestBuilders.post("/empresas/adherir")
                .header("X-API-KEY", "mi-api-key-secreta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // Espera un error 400
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdherirEmpresa_CuitDuplicado() throws Exception {
        // Configura los datos de prueba
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9"); // CUIT válido
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now());

        // Simula que el servicio lanza una excepción de CUIT duplicado
        doThrow(new DataIntegrityViolationException("CUIT duplicado"))
            .when(empresaService).adherirEmpresa(any(Empresa.class));

        // Realiza la solicitud POST
        mockMvc.perform(MockMvcRequestBuilders.post("/empresas/adherir")
                .header("X-API-KEY", "mi-api-key-secreta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(MockMvcResultMatchers.status().isConflict()); // Espera un error 409
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdherirEmpresa_Exitoso() throws Exception {
        // Configura los datos de prueba
        Empresa empresa = new Empresa();
        empresa.setCuit("30-20389875-9"); // CUIT válido
        empresa.setRazonSocial("King Gizzard");
        empresa.setFechaAdhesion(LocalDate.now());

        // Simula una solicitud POST exitosa y el encabezado de API Key
        mockMvc.perform(MockMvcRequestBuilders.post("/empresas/adherir")
                .header("X-API-KEY", "mi-api-key-secreta") // Agrega el encabezado de API Key
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empresa)))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Espera un código 200 (OK)
                .andExpect(MockMvcResultMatchers.content().string("Empresa adherida correctamente"));
    }
    
}