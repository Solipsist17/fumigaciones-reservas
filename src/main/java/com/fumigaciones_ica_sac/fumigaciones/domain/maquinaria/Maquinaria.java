package com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Maquinaria")
@Table(name = "maquinarias")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Maquinaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer cantidad;
    private Boolean activo;

    public void actualizar(Maquinaria maquinaria) {
        if (maquinaria.getNombre() != null) {
            this.nombre = maquinaria.getNombre();
        }
        if (maquinaria.getCantidad() != null) {
            this.cantidad = maquinaria.getCantidad();
        }
        if (maquinaria.getActivo() != null) {
            this.activo = maquinaria.getActivo();
        }
    }
}
