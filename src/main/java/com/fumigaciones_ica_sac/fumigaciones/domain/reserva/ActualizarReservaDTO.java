package com.fumigaciones_ica_sac.fumigaciones.domain.reserva;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ActualizarReservaDTO(
        @NotNull
        Long id,
        @JsonAlias("id_cliente")
        @NotNull Long idCliente,
        @JsonAlias("id_usuario")
        @NotNull Long idUsuario,
        @JsonAlias("id_servicio")
        @NotNull Long idServicio,
        @JsonAlias("cantidad_personal")
        @NotNull
        Integer cantidadPersonal,
        @JsonAlias("fecha_reserva")
        @Future
        @NotNull
        LocalDateTime fechaReserva,
        @NotBlank
        String descripcion) {
}
