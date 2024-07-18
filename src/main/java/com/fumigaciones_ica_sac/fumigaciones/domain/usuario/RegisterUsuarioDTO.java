package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUsuarioDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String clave,
        @NotNull
        Boolean activo,
        @NotNull
        Rol rol) {
}
