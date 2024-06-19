package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import java.math.BigDecimal;

public record ListadoFacturaDTO(Long id, Long idReserva, Integer cantidadHora, BigDecimal precioUnitario, BigDecimal total) {
    public ListadoFacturaDTO(Factura factura) {
        this(factura.getId(), factura.getReserva().getId(), factura.getCantidadHora(), factura.getPrecioUnitario(), factura.getTotal());
    }
}
