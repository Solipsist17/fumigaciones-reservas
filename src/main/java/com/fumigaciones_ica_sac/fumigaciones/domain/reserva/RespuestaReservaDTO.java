package com.fumigaciones_ica_sac.fumigaciones.domain.reserva;

import java.time.LocalDateTime;

public record RespuestaReservaDTO(Long id, Long idCliente, Long idUsuario, Long idServicio, Integer cantidadPersonal,
                                  LocalDateTime fechaReserva, String descripcion) {
    public RespuestaReservaDTO(Reserva reserva) {
        this(reserva.getId(), reserva.getCliente().getId(), reserva.getUsuario().getId(), reserva.getServicio().getId(),
                reserva.getCantidadPersonal(), reserva.getFechaReserva(), reserva.getDescripcion());
    }
}
