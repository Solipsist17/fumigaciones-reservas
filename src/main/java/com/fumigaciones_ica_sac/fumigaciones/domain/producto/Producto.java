package com.fumigaciones_ica_sac.fumigaciones.domain.producto;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Producto")
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer cantidad;
    private Boolean activo;
    private String descripcion;

    public void actualizar(Producto producto) {
        if (producto.getNombre() != null) {
            this.nombre = producto.getNombre();
        }
        if (producto.getCantidad() != null) {
            this.cantidad = producto.getCantidad();
        }
        if (producto.getActivo() != null) {
            this.activo = producto.getActivo();
        }
        if (producto.getDescripcion() != null) {
            this.descripcion = producto.getDescripcion();
        }
    }
}
