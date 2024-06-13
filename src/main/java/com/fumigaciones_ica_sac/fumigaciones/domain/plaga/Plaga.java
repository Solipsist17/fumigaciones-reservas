package com.fumigaciones_ica_sac.fumigaciones.domain.plaga;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Plaga")
@Table(name = "plagas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean activo;

    public void actualizar(Plaga plaga) {
        if (plaga.getNombre() != null) {
            this.nombre = plaga.getNombre();
        }
        if (plaga.getActivo() != null) {
            this.activo = plaga.getActivo();
        }
    }
}
