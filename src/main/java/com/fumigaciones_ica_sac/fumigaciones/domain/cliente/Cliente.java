package com.fumigaciones_ica_sac.fumigaciones.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String dni;
    private String telefono;
    @Column(name = "activo")
    private Boolean activo;

    public Cliente(RegistrarClienteDTO dto) {
        this.nombre = dto.nombre();
        this.apellido = dto.apellido();
        this.sexo = dto.sexo();
        this.dni = dto.dni();
        this.telefono = dto.telefono();
        this.activo = dto.activo();
    }

    public void actualizar(Cliente cliente) {
        if (cliente.getNombre() != null) {
            this.nombre = cliente.getNombre();
        }
        if (cliente.getApellido() != null) {
            this.apellido = cliente.getApellido();
        }
        if (cliente.getSexo() != null) {
            this.sexo = cliente.getSexo();
        }
        if (cliente.getDni() != null) {
            this.dni = cliente.getDni();
        }
        if (cliente.getTelefono() != null) {
            this.telefono = cliente.getTelefono();
        }
        if (cliente.getActivo() != null) {
            this.activo = cliente.getActivo();
        }
    }
}
