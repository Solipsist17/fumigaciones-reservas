package com.fumigaciones_ica_sac.fumigaciones.domain.servicio;

import com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria.Maquinaria;
import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.Plaga;
import com.fumigaciones_ica_sac.fumigaciones.domain.producto.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity(name = "Servicio")
@Table(name = "servicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquinaria")
    private Maquinaria maquinaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plaga")
    private Plaga plaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    private String nombre;

    @Column(name = "cantidad_maquinaria")
    private Integer cantidadMaquinaria;

    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    @Column(name = "precio_hora")
    private BigDecimal precioHora;

    private String descripcion;

    public void actualizar(ActualizarServicioDTO servicioDTO) {
        if (servicioDTO.idMaquinaria() != null) {
            this.maquinaria.setId(servicioDTO.idMaquinaria());
        }
        if (servicioDTO.idPlaga() != null) {
            this.plaga.setId(servicioDTO.idPlaga());
        }
        if (servicioDTO.idProducto() != null) {
            this.producto.setId(servicioDTO.idProducto());
        }
        if (servicioDTO.nombre() != null) {
            this.nombre = servicioDTO.nombre();
        }
        if (servicioDTO.cantidadMaquinaria() != null) {
            this.cantidadMaquinaria = servicioDTO.cantidadMaquinaria();
        }
        if (servicioDTO.cantidadProducto() != null) {
            this.cantidadProducto = servicioDTO.cantidadProducto();
        }
        if (servicioDTO.precioHora() != null) {
            this.precioHora = servicioDTO.precioHora();
        }
        if (servicioDTO.descripcion() != null) {
            this.descripcion = servicioDTO.descripcion();
        }
    }
/*
    public void actualizar(ActualizarServicioDTO servicioDTO) {
        if (servicioDTO.idMaquinaria() != null) {
            this.maquinaria.setId(); = servicioDTO.idMaquinaria();
        }
        if (servicio.getPlaga() != null) {
            this.plaga = servicio.getPlaga();
        }
        if (servicio.getProducto() != null) {
            this.producto = servicio.getProducto();
        }
        if (servicio.getNombre() != null) {
            this.nombre = servicio.getNombre();
        }
        if (servicio.getCantidadMaquinaria() != null) {
            this.cantidadMaquinaria = servicio.getCantidadMaquinaria();
        }
        if (servicio.getCantidadProducto() != null) {
            this.cantidadProducto = servicio.getCantidadProducto();
        }
        if (servicio.getPrecioHora() != null) {
            this.precioHora = servicio.getPrecioHora();
        }
        if (servicio.getDescripcion() != null) {
            this.descripcion = servicio.getDescripcion();
        }
    }*/
}
