package com.fumigaciones_ica_sac.fumigaciones.domain.usuario;

import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.ActualizarUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String clave;
    @Column(name = "activo")
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Usuario(Long id) {
        this.id = id;
    }

    public void actualizar(ActualizarUsuarioDTO usuarioDTO) {
        if (usuarioDTO.nombre() != null) {
            this.nombre = usuarioDTO.nombre();
        }
        if (usuarioDTO.clave() != null) {
            this.clave = usuarioDTO.clave();
        }
        if (usuarioDTO.activo() != null) {
            this.activo = usuarioDTO.activo();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));

        /*
        return  Arrays.stream(Rol.values())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        */
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }
}
