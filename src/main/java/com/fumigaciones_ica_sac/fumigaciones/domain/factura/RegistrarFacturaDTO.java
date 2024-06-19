package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegistrarFacturaDTO(
        @JsonAlias("id_reserva")
        @NotNull
        Long idReserva,
        @JsonAlias("cantidad_hora")
        @NotNull
        Integer cantidadHora,
        @JsonAlias("precio_unitario")
        @NotNull
        @Min(0)
        BigDecimal precioUnitario,
        @NotNull
        @Min(0)
        BigDecimal total) {
}
