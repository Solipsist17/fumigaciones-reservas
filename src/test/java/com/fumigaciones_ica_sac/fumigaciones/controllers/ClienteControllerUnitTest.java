package com.fumigaciones_ica_sac.fumigaciones.controllers;

import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class ClienteControllerUnitTest {

    private final ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class); // Mock del repositorio
    private final ClienteController clienteController = new ClienteController(clienteRepository); // Controlador con el mock

    @Test
    @DisplayName("Debería retornar estado HTTP 201 (Created) y el DTO de cliente cuando los datos son válidos")
    void registrarEscenarioExitoso() {
        // Arrange
        RegistrarClienteDTO registrarClienteDTO = new RegistrarClienteDTO(
                "Sebastian", "Vasquez", Sexo.MASCULINO, "75579862", "987144064", true);

        Cliente cliente = new Cliente(registrarClienteDTO);
        cliente.setId(1L);


        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente); // configurar el mock del repositorio

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance(); // uri base para construir la ubicación del recurso creado

        // Act
        ResponseEntity<RespuestaClienteDTO> response = clienteController.registrar(registrarClienteDTO, uriBuilder);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // Verificar el estado HTTP
        assertThat(response.getBody()).isNotNull(); // Verificar que el cuerpo no sea nulo
        assertThat(response.getBody().id()).isEqualTo(1L); // Verificar que el ID del cliente coincida
        assertThat(response.getHeaders().getLocation()).isEqualTo(URI.create("/clientes/1")); // Verificar la URI del recurso creado

        // Verificar que el repositorio fue llamado con un cliente
        Mockito.verify(clienteRepository).save(any(Cliente.class));
    }
}