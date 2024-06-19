package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.ActualizarReservaDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.Reserva;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "Factura")
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @Column(name = "cantidad_hora")
    private Integer cantidadHora;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    private BigDecimal total;


    public void actualizar(ActualizarFacturaDTO facturaDTO) {
        if (facturaDTO.idReserva() != null) {
            this.reserva.setId(facturaDTO.idReserva());
        }
        if (facturaDTO.cantidadHora() != null) {
            this.cantidadHora = facturaDTO.cantidadHora();
        }
        if (facturaDTO.precioUnitario() != null) {
            this.precioUnitario = facturaDTO.precioUnitario();
        }
        if (facturaDTO.total() != null) {
            this.total = facturaDTO.total();
        }
    }
}
