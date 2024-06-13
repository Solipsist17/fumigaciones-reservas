package com.fumigaciones_ica_sac.fumigaciones.domain.servicio;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ActualizarServicioDTO(
        @NotNull
        Long id,
        @JsonAlias("id_maquinaria")
        @NotNull Long idMaquinaria,
        @JsonAlias("id_plaga")
        @NotNull Long idPlaga,
        @JsonAlias("id_producto")
        @NotNull Long idProducto,
        @NotBlank
        String nombre,
        @JsonAlias("cantidad_maquinaria")
        @NotNull
        Integer cantidadMaquinaria,
        @JsonAlias("cantidad_producto")
        @NotNull
        Integer cantidadProducto,
        @JsonAlias("precio_hora")
        @NotNull
        BigDecimal precioHora,
        @NotBlank
        String descripcion) {
}
