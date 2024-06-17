package com.fumigaciones_ica_sac.fumigaciones.domain.reserva;

import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.Cliente;
import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.Servicio;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Reserva")
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;

    @Column(name = "cantidad_personal")
    private Integer cantidadPersonal;

    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;

    private String descripcion;

    public void actualizar(ActualizarReservaDTO reservaDTO) {
        if (reservaDTO.idCliente() != null) {
            this.cliente.setId(reservaDTO.idCliente());
        }
        if (reservaDTO.idUsuario() != null) {
            this.usuario.setId(reservaDTO.idUsuario());
        }
        if (reservaDTO.idServicio() != null) {
            this.servicio.setId(reservaDTO.idServicio());
        }
        if (reservaDTO.cantidadPersonal() != null) {
            this.cantidadPersonal = reservaDTO.cantidadPersonal();
        }
        if (reservaDTO.fechaReserva() != null) {
            this.fechaReserva = reservaDTO.fechaReserva();
        }
        if (reservaDTO.descripcion() != null) {
            this.descripcion = reservaDTO.descripcion();
        }
    }
}
