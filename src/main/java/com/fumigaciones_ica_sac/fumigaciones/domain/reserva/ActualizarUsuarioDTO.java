package com.fumigaciones_ica_sac.fumigaciones.domain.reserva;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
        @NotNull
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        String clave,
        @NotNull
        Boolean activo) {
}
