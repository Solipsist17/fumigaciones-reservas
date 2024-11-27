package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc // configura el objeto MockMvc que simula el entorno de un servidor
@AutoConfigureJsonTesters // facilita el manejo de objetos JSON en entorno de prueba
@ActiveProfiles("test")
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc; // enviar peticiones
    @Autowired
    private EntityManager em; // capa de persistencia

    @Autowired
    private JacksonTester<RegistrarClienteDTO> registrarClienteDTOJacksonTester;
    @Autowired
    private JacksonTester<RespuestaClienteDTO> respuestaClienteDTOJacksonTester;

    @Test
    @DisplayName("Debería retornar estado HTTP 400 (bad request) cuando los datos ingresados sean inválidos")
    @WithMockUser // componente de seguridad
    @Transactional
    void registrarEscenario1() throws Exception {
        // arrange
        var cliente = registrarCliente("Sebastian", "Vasquez", Sexo.MASCULINO, "75579862",
                        "987144064", Boolean.TRUE);

        // act
        var response = mvc.perform(MockMvcRequestBuilders.post("/clientes"))
                .andReturn()
                .getResponse();

        // assert
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería retornar estado HTTP 201 (created) cuando los datos ingresados sean válidos")
    @WithMockUser
    @Transactional
    void registrarEscenario2() throws Exception {
        // arrange
        var datos = new RespuestaClienteDTO(1L, "Sebastian", "Vasquez", Sexo.MASCULINO,
                "75579862", "987144064", Boolean.TRUE);

        // act
        var response = mvc.perform(MockMvcRequestBuilders.post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registrarClienteDTOJacksonTester.write(new RegistrarClienteDTO("Sebastian", "Vasquez",
                        Sexo.MASCULINO, "75579862", "987144064", Boolean.TRUE)).getJson())
        ).andReturn().getResponse();

        // assert
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = respuestaClienteDTOJacksonTester.write(datos).getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado); // ¿? json retornado = json esperado
    }

    private Cliente registrarCliente(String nombre, String apellido, Sexo sexo, String dni, String telefono, Boolean activo) {
        RegistrarClienteDTO dto = new RegistrarClienteDTO(nombre, apellido, sexo, dni, telefono, activo);
        var cliente = new Cliente(dto);
        em.persist(cliente);
        return cliente;
    }

}
