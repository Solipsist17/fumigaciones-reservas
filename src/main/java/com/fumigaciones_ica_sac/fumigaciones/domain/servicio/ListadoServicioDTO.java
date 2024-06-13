package com.fumigaciones_ica_sac.fumigaciones.domain.servicio;

import java.math.BigDecimal;

public record ListadoServicioDTO(Long id, Long idMaquinaria, Long idPlaga, Long idProducto, String nombre, Integer cantidadMaquinaria,
                                 Integer cantidadProducto, BigDecimal precioHora, String descripcion) {
    public ListadoServicioDTO(Servicio servicio) {
        this(servicio.getId(), servicio.getMaquinaria().getId(), servicio.getPlaga().getId(), servicio.getProducto().getId(),
                servicio.getNombre(), servicio.getCantidadMaquinaria(), servicio.getCantidadProducto(), servicio.getPrecioHora(), servicio.getDescripcion());
    }
}
