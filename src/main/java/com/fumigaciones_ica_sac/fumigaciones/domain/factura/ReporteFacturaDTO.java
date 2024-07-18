package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteFacturaDTO {
    private Long id;
    private Long idReserva;
    private Integer cantidadHora;
    private BigDecimal precioUnitario;
    private BigDecimal total;

    public ReporteFacturaDTO(Factura factura) {
        this.id = factura.getId();
        this.idReserva = factura.getReserva().getId();
        this.cantidadHora = factura.getCantidadHora();
        this.precioUnitario = factura.getPrecioUnitario();
        this.total = factura.getTotal();
    }
}
