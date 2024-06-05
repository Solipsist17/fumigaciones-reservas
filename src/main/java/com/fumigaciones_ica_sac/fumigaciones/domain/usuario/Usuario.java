package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clave;
    @Column(name = "estado")
    private Boolean estado;

    public void actualizar(Usuario usuario) {
        if (usuario.getNombre() != null) {
            this.nombre = usuario.getNombre();
        }
        if (usuario.getClave() != null) {
            this.clave = usuario.getClave();
        }
        if (usuario.getEstado() != null) {
            this.estado = usuario.getEstado();
        }
    }
}
