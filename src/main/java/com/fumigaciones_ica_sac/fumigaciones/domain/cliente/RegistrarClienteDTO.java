package com.fumigaciones_ica_sac.fumigaciones.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarClienteDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotNull
        Sexo sexo,
        @NotBlank
        String dni,
        @NotBlank
        String telefono,
        @NotNull
        Boolean activo) {
}
